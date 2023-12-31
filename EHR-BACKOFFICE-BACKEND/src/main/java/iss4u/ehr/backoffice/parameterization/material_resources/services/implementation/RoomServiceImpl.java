package iss4u.ehr.backoffice.parameterization.material_resources.services.implementation;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.Equipment;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Room;
import iss4u.ehr.backoffice.parameterization.material_resources.repositories.RoomRepository;
import iss4u.ehr.backoffice.parameterization.material_resources.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void create(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> retrieveRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getRoomByKy(Long roomKy) {
        return roomRepository.findById(roomKy);
    }

    @Override
    public Room getRoomByNm(String roomLabel) {
        return roomRepository.findByRoomLabel(roomLabel);
    }

    @Override
    public String getRoomNameByKey(Long roomKy) {
        Optional<Room> roomOptional = roomRepository.findById(roomKy);
        return roomOptional.isPresent() ? roomOptional.get().getRoomLabel() : "";
    }

    @Override
    public void update(Long roomKy, Room updatedRoom) {
        Room existingRoom = roomRepository.findById(roomKy)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + roomKy + " not found"));

        // Update the properties

        existingRoom.setRoomLabel(updatedRoom.getRoomLabel());
        existingRoom.setRoomType(updatedRoom.getRoomType());
        if (updatedRoom.getRoomClass() != null) {
            existingRoom.setRoomClass(updatedRoom.getRoomClass());
        }
        existingRoom.setRoomUnxTmUpdt(updatedRoom.getRoomUnxTmUpdt());

        existingRoom.setRoomGrp(existingRoom.getRoomGrp());
        roomRepository.save(existingRoom);
    }

    @Override
    public void delete(Long roomKy) {
        roomRepository.deleteById(roomKy);
    }

    @Override
    public List<Equipment> getChildElements(Room room) {
        return room.getEquipments();
    }

    @Override
    public void addChildElement(Room room, Equipment equipment) {
        List<Equipment> equipments = room.getEquipments();
        equipments.add(equipment);
        equipment.setRoom(room);
        roomRepository.save(room);
    }
}
