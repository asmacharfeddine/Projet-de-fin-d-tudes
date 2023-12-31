package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatmentCategory;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PhysicalTreatmentCategoryRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PhysicalTreatmentRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.PhysicalTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PhysicalTreatmentImpl implements PhysicalTreatmentService {
    @Autowired
    private PhysicalTreatmentRepository physicalTreatmentRepository;
    @Autowired
    private PhysicalTreatmentCategoryRepository categoryRepository; // Assuming you have a category repository

    public PhysicalTreatment createPhysicalTreatment(PhysicalTreatment physicalTreatment){
        return physicalTreatmentRepository.save(physicalTreatment);
    }
    public List<PhysicalTreatment> getAllPhysicalTreatments(){
        return physicalTreatmentRepository.findAll();
    }
    public PhysicalTreatment updatePhysicalTreatment(@RequestBody PhysicalTreatment physicalTreatment, Integer physicalTreatment_Key){
        PhysicalTreatment existingPhysicalTreatment = this.physicalTreatmentRepository.findById(physicalTreatment_Key)
                .orElseThrow(() -> new ResourceNotFoundException("Physical treatment not found with PhysicalTreatment_Key : " + physicalTreatment_Key));
        existingPhysicalTreatment.setTreatmentName(physicalTreatment.getTreatmentName());
        existingPhysicalTreatment.setTreatmentDescription(physicalTreatment.getTreatmentDescription());
        existingPhysicalTreatment.setDuration(physicalTreatment.getDuration());
        existingPhysicalTreatment.setNotes(physicalTreatment.getNotes());
        return this.physicalTreatmentRepository.save(existingPhysicalTreatment);
    }

/*----------------------------------------------------------------
    @Transactional
    public PhysicalTreatment updatePhysicalTreatmentWithCategory(Integer physicalTreatment_Key, PhysicalTreatment updatedPhysicalTreatment) {
        PhysicalTreatment existingPhysicalTreatment = physicalTreatmentRepository.findById(physicalTreatment_Key)
                .orElseThrow(() -> new EntityNotFoundException("ParentEntity not found with id: " + physicalTreatment_Key));
        // Update properties of the existing parent
        existingPhysicalTreatment.setTreatmentName(updatedPhysicalTreatment.getTreatmentName());
        existingPhysicalTreatment.setTreatmentDescription(updatedPhysicalTreatment.getTreatmentDescription());
        existingPhysicalTreatment.setNotes(updatedPhysicalTreatment.getNotes());
        existingPhysicalTreatment.setDuration(updatedPhysicalTreatment.getDuration());


        Integer categoryId = updatedPhysicalTreatment.getPhysicalTreatmentCategory().getPhysicalTreatmentCategory_Key();
        PhysicalTreatmentCategory updatedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        // Update the category of the existing parent
         existingPhysicalTreatment.setPhysicalTreatmentCategory(updatedCategory);

        // Save the updated ParentEntity
        return physicalTreatmentRepository.save(existingPhysicalTreatment);



    }----------------------------------------------------*/



    public PhysicalTreatment updatePhysicalTreatmentWithCategory(Integer physicalTreatment_Key, PhysicalTreatment updatedPhysicalTreatment) {
        PhysicalTreatment existingPhysicalTreatment = physicalTreatmentRepository.findById(physicalTreatment_Key)
                .orElseThrow(() -> new EntityNotFoundException("ParentEntity not found with id: " + physicalTreatment_Key));
        // Update properties of the existing parent

        existingPhysicalTreatment.setPhysicalTreatment_Key(existingPhysicalTreatment.getPhysicalTreatment_Key());

        existingPhysicalTreatment.setTreatmentName(updatedPhysicalTreatment.getTreatmentName());

        if(updatedPhysicalTreatment.getTreatmentDescription()!= null){
            existingPhysicalTreatment.setTreatmentDescription(updatedPhysicalTreatment.getTreatmentDescription());
        }

        if(updatedPhysicalTreatment.getNotes()!= null){
            existingPhysicalTreatment.setNotes(updatedPhysicalTreatment.getNotes());
        }

        if(updatedPhysicalTreatment.getDuration()!= null){
            existingPhysicalTreatment.setDuration(updatedPhysicalTreatment.getDuration());
        }

        if (updatedPhysicalTreatment.getPhysicalTreatmentCategory() != null){
        Integer physicalTreatmentCategory_key = updatedPhysicalTreatment.getPhysicalTreatmentCategory().getPhysicalTreatmentCategory_Key();
        PhysicalTreatmentCategory updatedCategory = categoryRepository.findById(physicalTreatmentCategory_key)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + physicalTreatmentCategory_key));

        // Update the category of the existing parent
         existingPhysicalTreatment.setPhysicalTreatmentCategory(updatedCategory);}
           /* existingPhysicalTreatment.getPhysicalTreatmentCategory().
                    setPhysicalTreatmentCategory_Key(physicalTreatmentCategory_key);}*/


            // Save the updated ParentEntity
        return physicalTreatmentRepository.save(existingPhysicalTreatment);

    }


   /* @Transactional
    public PhysicalTreatment updatePhT(Integer physicalTreatment_Key, Integer physicalTreatmentCatgory_Key){
        PhysicalTreatment physicalTreatment = physicalTreatmentRepository.findById(physicalTreatment_Key).orElseThrow(() -> new ResourceNotFoundException("Physical Treatment not found"));
        PhysicalTreatmentCategory physicalTreatmentCategory = categoryRepository.findById(physicalTreatmentCatgory_Key).orElseThrow(() -> new ResourceNotFoundException("Physical Treatment Category not found"));
        physicalTreatment.setTreatmentName();

    }*/

    /*@PutMapping("/{id}")
    public ResponseEntity<PhysicalTreatment> updatePhysicalTreatmentt(@RequestBody PhysicalTreatment physicalTreatment, @PathVariable Integer id) {
        PhysicalTreatment existingPhysicalTreatment = this.physicalTreatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Physical treatment not found with id: " + id));

        // Update properties of the existing physical treatment
        existingPhysicalTreatment.setTreatmentName(physicalTreatment.getTreatmentName());
        existingPhysicalTreatment.setTreatmentDescription(physicalTreatment.getTreatmentDescription());
        existingPhysicalTreatment.setDuration(physicalTreatment.getDuration());
        existingPhysicalTreatment.setNotes(physicalTreatment.getNotes());

        // Fetch and update the category
        PhysicalTreatmentCategory updatedCategory = categoryRepository.findById(physicalTreatment.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + physicalTreatment.getCategory().getId()));
        existingPhysicalTreatment.setCategory(updatedCategory);

        PhysicalTreatment updatedTreatment = physicalTreatmentRepository.save(existingPhysicalTreatment);
        return ResponseEntity.ok(updatedTreatment);
    }*/
    /*public PhysicalTreatment findPhysicalTreatmentByName(String treatmentName ){
        return physicalTreatmentRepository.findPhysicalTreatmentByName(treatmentName);
    }*/
    public PhysicalTreatment findPhysicalTreatmentBytreatmentName(String treatmentName){
        return physicalTreatmentRepository.findPhysicalTreatmentBytreatmentName(treatmentName);
    }

    public ResponseEntity<PhysicalTreatment> deletePhysicalTreatmentById(Integer Id){
        PhysicalTreatment existingPhysicalTreatment = this.physicalTreatmentRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Physical Treatment not found with PhysicalTreatment_Key : "+ Id));
        this.physicalTreatmentRepository.delete(existingPhysicalTreatment);
        return ResponseEntity.ok().build();

    }

   /* public List<PhysicalTreatment>GetTreatmentsByCategoryName(String categoryName, List<PhysicalTreatment> physicalTreatments) {
        List<PhysicalTreatment> physicalTreatmentsSelectedWithCategoryName = new ArrayList<>();
        for (PhysicalTreatment physicalTreatment : physicalTreatments) {
            if (physicalTreatment.getPhysicalTreatmentCategory().getCategoryName() == categoryName) {
                physicalTreatmentsSelectedWithCategoryName.add(physicalTreatment);
            }
        }
        return physicalTreatmentsSelectedWithCategoryName;
    }*/


    // to use after reading categoryName in the front and sending the corresponding id of the selected name
    /*public List<PhysicalTreatment> findTreatmentsByCategoryId(Integer category_id){
        for (PhysicalTreatment)
        //return physicalTreatmentRepository.findTreatmentsByCategoryId(category_id);
    }*/

    /*public  List<PhysicalTreatment> findTreatmentsByCategoryId(List<PhysicalTreatment> physicalTreatments, Integer Id) {
        List<PhysicalTreatment> liste = new ArrayList<>();
        for (PhysicalTreatment physicalTreatment : physicalTreatments) {
            if (physicalTreatment.getPhysicalTreatmentCategory().getPhysicalTreatmentCategory_Key() == Id) {
                liste.add(physicalTreatment);
            }
        }
        return liste;
    }*/

    public boolean checkTreatmentNameExists(String treatmentName) {
        return physicalTreatmentRepository.existsByTreatmentName(treatmentName);
    }


    public long countPhysicalTreatments() {
        return physicalTreatmentRepository.count();
    }
    }



