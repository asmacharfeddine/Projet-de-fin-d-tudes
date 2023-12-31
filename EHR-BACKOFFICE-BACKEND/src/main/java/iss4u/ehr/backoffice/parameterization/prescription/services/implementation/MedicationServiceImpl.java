package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;


import iss4u.ehr.backoffice.parameterization.prescription.entities.ActiveIngredient;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Medication;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.ActiveIngredientsRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.MedicationRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

import java.util.List;
import java.util.Set;
@Service
public class MedicationServiceImpl implements MedicationService {
    // Inject MedicationRepository
    @Autowired
    private final MedicationRepository medicationRepository;
    @Autowired
    private final ActiveIngredientsRepository activeIngredientsRepository;

   // private List<Integer> activeIngredientIds;

    public MedicationServiceImpl(MedicationRepository medicationRepository, ActiveIngredientsRepository activeIngredientsRepository) {
        this.medicationRepository = medicationRepository;
        this.activeIngredientsRepository = activeIngredientsRepository;
    }

    public void addActiveIngredientToMedication(Integer medicationId, Integer ActiveIngredientId){
        Medication medication = medicationRepository.findById(medicationId).orElseThrow(() -> new RuntimeException("Medication not found"));
        ActiveIngredient activeIngredient = activeIngredientsRepository.findById(ActiveIngredientId).orElseThrow(() ->new RuntimeException(" Active ingredient not found"));

        medication.getActiveIngredients().add(activeIngredient);
        activeIngredient.getMedications().add(medication);

        medicationRepository.save(medication);
        activeIngredientsRepository.save(activeIngredient);

    }


   public void removeActiveIngredientFromMedication(Integer medicationId, Integer ActiveIngredientId){
        Medication medication = medicationRepository.findById(medicationId).orElseThrow(() -> new RuntimeException("Medication not found"));
        ActiveIngredient activeIngredient = activeIngredientsRepository.findById(ActiveIngredientId).orElseThrow(() ->new RuntimeException(" Active ingredient not found"));

        // Remove the activeIngredient from the medication's activeIngredients
        medication.getActiveIngredients().remove(activeIngredient);
        // Remove the medication from activeIngredient's medications
        activeIngredient.getMedications().remove(medication);

        medicationRepository.save(medication);
        activeIngredientsRepository.save(activeIngredient);
    }

    public Medication createMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public List<Medication> getAllMedications(){return medicationRepository.findAll();}
   public  Medication getMedicationById(Integer Id){
        return this.medicationRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with Medication_Key : " + Id));
    }


    public Medication updateMedication(@RequestBody Medication medication, Integer Id){
        Medication existingMedication = this.medicationRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with Medication_Key : " + Id));
        existingMedication.setCode(medication.getCode());
        existingMedication.setName(medication.getName());
        existingMedication.setType(medication.getType());
        existingMedication.setDosageForm(medication.getDosageForm());
        existingMedication.setForce(medication.getForce());
        return this.medicationRepository.save(existingMedication);

    }
    public ResponseEntity<Medication> deleteMedicationById(Integer Id){
        Medication existingMedication = this.medicationRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with Medication_Key : "+ Id));
                 this.medicationRepository.delete(existingMedication);
                 return ResponseEntity.ok().build();

    }

   public Medication findMedicationById(Integer Id){
        return medicationRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with id : " + Id));
   }
   public  Medication findMedicationByName(String name) {
       return medicationRepository.findMedicationByName(name);
   }
    public Medication findMedicationByCode(String code){ return  medicationRepository.findMedicationByCode(code);}
    public List<Medication>  findMedicationByDosageForm(String dosageForm){return  medicationRepository.findMedicationByDosageForm(dosageForm);}
    public List<Medication> findMedicationByForce(Float force){return medicationRepository.findMedicationByForce(force);}

      /* public Medication addActiveIngredientsToMedication(Integer medicationId, Set<Integer> activeIngredientsIds){
        // Rechercher le médicament par son ID
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException("Médicament introuvable avec l'ID : " + medicationId));

        // Récupérer les ingrédients actifs à partir de leurs IDs
        List<ActiveIngredient> selectedActiveIngredients = activeIngredientsRepository.findAllById(activeIngredientsIds);

        // Associer les ingrédients actifs sélectionnés au médicament
        medication.getActiveIngredients().addAll(selectedActiveIngredients);


        // Enregistrer les modifications dans la base de données
        return medicationRepository.save(medication);
    }
*/


    // Adding a medication with or without a List of active Ingredients
    //@Transactional
    public Medication addMedication(Medication medication, List<Integer> activeIngredientIds) {
        if (activeIngredientIds != null && !activeIngredientIds.isEmpty()) {
            List<ActiveIngredient> selectedActiveIngredients = activeIngredientsRepository.findAllById(activeIngredientIds);

            medication.setActiveIngredients(new HashSet<>(selectedActiveIngredients));
        } else {
            // If no active ingredient IDs provided, set an empty set to clear any existing associations
            medication.setActiveIngredients(new HashSet<>());
        }
        System.out.println("Active Ingredient IDs: " + activeIngredientIds);
        System.out.println("Medication: " + medication);
        // Save the medication to the database
        return medicationRepository.save(medication);
    }


    // updatedMedication with or without ActiveIngredients
    public Medication updatedMedicationwithActiveIngredients(Integer medication_Key, Medication updatedMedication){
        Medication existingMedication = medicationRepository.findById(medication_Key)
                .orElseThrow(()-> new EntityNotFoundException("Medication not found with id: " + medication_Key));
        // Update properties of existing medication
        existingMedication.setMedication_Key(existingMedication.getMedication_Key());

        existingMedication.setCode(updatedMedication.getCode());

        existingMedication.setName(updatedMedication.getName());

        if (updatedMedication.getDosageForm() != null){
            existingMedication.setDosageForm(updatedMedication.getDosageForm());
        }

        if (updatedMedication.getType() != null){
            existingMedication.setType(updatedMedication.getType());
        }

        if (updatedMedication.getForce() != null){
            existingMedication.setForce(updatedMedication.getForce());
        }

        if (updatedMedication.getActiveIngredients() != null && !updatedMedication.getActiveIngredients().isEmpty()) {
            // Set the selected ActiveIngredients for the Medication
            existingMedication.setActiveIngredients(new HashSet<>(updatedMedication.getActiveIngredients()));
        }
        if (updatedMedication.getActiveIngredients() == null || updatedMedication.getActiveIngredients().isEmpty()) {
            existingMedication.setActiveIngredients(new HashSet<>());
        }
        if (updatedMedication.getActiveIngredients() != null && updatedMedication.getActiveIngredients().isEmpty()) {
            existingMedication.setActiveIngredients(new HashSet<>(existingMedication.getActiveIngredients()));
        }


      return medicationRepository.save(existingMedication);
    }


    /*public PhysicalTreatment updatePhysicalTreatmentWithCategory(Integer physicalTreatment_Key, PhysicalTreatment updatedPhysicalTreatment) {
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



    // Save the updated ParentEntity
        return physicalTreatmentRepository.save(existingPhysicalTreatment);

}*/



    public long countMedications() {
        return medicationRepository.count();
    }

}