package iss4u.ehr.backoffice.parameterization.material_resources.services.implementation;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.Bed;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Equipment;
import iss4u.ehr.backoffice.parameterization.material_resources.repositories.EquipmentRepository;
import iss4u.ehr.backoffice.parameterization.material_resources.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public void create(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> retrieveEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> getEquipmentByKy(Long eqpmntKy) {
        return equipmentRepository.findById(eqpmntKy);
    }

    @Override
    public Equipment getEquipmentByLabel(String eqpmntLabel) {
        return equipmentRepository.findByEquipmentLabel(eqpmntLabel);
    }

    @Override
    public String getEquipmentNameByKey(Long eqpmntKy) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(eqpmntKy);
        return equipmentOptional.isPresent() ? equipmentOptional.get().getEquipmentLabel() : "";
    }

    @Override
    public void update(Long eqpmntKy, Equipment updatedEquipment) {
        Equipment existingEquipment = equipmentRepository.findById(eqpmntKy)
                .orElseThrow(() -> new EntityNotFoundException("Equipment with id " + eqpmntKy + " not found"));

        // Update the properties
        existingEquipment.setEquipmentLabel(updatedEquipment.getEquipmentLabel());
        existingEquipment.setDescription(updatedEquipment.getDescription());
        existingEquipment.setAddressIp(updatedEquipment.getAddressIp());
        existingEquipment.setAddressMac(updatedEquipment.getAddressMac());
        existingEquipment.setEquipmentUnxTmUpdt(updatedEquipment.getEquipmentUnxTmUpdt());

        equipmentRepository.save(existingEquipment);
    }

    @Override
    public void delete(Long equipmentKy) {
        equipmentRepository.deleteById(equipmentKy);
    }

    @Override
    public List<Bed> getChildElements(Equipment equipment) {
        return equipment.getBeds();
    }

    @Override
    public void addChildElement(Equipment equipment, Bed bed) {
        List<Bed> beds = equipment.getBeds();
        beds.add(bed);
        bed.setEquipment(equipment);
        equipmentRepository.save(equipment);
    }
}
