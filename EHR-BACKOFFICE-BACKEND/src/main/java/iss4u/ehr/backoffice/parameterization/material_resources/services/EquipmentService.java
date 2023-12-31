package iss4u.ehr.backoffice.parameterization.material_resources.services;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.Bed;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Equipment;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Room;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.RoomGrp;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    void create(Equipment equipment);

    List<Equipment> retrieveEquipments();

    Optional<Equipment> getEquipmentByKy(Long eqpmntKy);

    Equipment getEquipmentByLabel(String eqpmntLabel);

    String getEquipmentNameByKey(Long eqpmntKy);

    void update(Long eqpmntKy, Equipment updatedEquipment);

    void delete(Long eqpmntKy);

    List<Bed> getChildElements(Equipment equipment);

    void addChildElement(Equipment equipment, Bed bed);
}
