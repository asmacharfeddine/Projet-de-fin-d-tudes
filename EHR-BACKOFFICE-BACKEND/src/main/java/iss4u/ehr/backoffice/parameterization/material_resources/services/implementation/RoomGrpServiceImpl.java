package iss4u.ehr.backoffice.parameterization.material_resources.services.implementation;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.RoomGrp;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Room;
import iss4u.ehr.backoffice.parameterization.material_resources.repositories.RoomGrpRepository;
import iss4u.ehr.backoffice.parameterization.material_resources.services.RoomGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomGrpServiceImpl implements RoomGrpService {
    private final RoomGrpRepository roomGrpRepository;

    @Autowired
    public RoomGrpServiceImpl(RoomGrpRepository roomGrpRepository) {
        this.roomGrpRepository = roomGrpRepository;
    }

    @Override
    public void create(RoomGrp roomGrp) {
        roomGrpRepository.save(roomGrp);
    }

    @Override
    public List<RoomGrp> retrieveRoomGroups() {
        return roomGrpRepository.findAll();
    }

    @Override
    public Optional<RoomGrp> getRoomGrpByKy(Long roomGrpKy) {
        return roomGrpRepository.findById(roomGrpKy);
    }

    @Override
    public RoomGrp getRoomGrpByNm(String roomGrpNm) {
        return roomGrpRepository.findByRoomGrpNm(roomGrpNm);
    }

    @Override
    public String getRoomGrpNameByKey(Long roomGrpKy) {
        Optional<RoomGrp> roomGrpOptional = roomGrpRepository.findById(roomGrpKy);
        return roomGrpOptional.isPresent() ? roomGrpOptional.get().getRoomGrpNm() : "";
    }

    @Override
    public void update(Long roomGrpKy, RoomGrp updatedRoomGrp) {
        RoomGrp existingRoomGrp = roomGrpRepository.findById(roomGrpKy)
                .orElseThrow(() -> new EntityNotFoundException("Room group with id " + roomGrpKy + " not found"));

        // Update the properties
        existingRoomGrp.setRoomGrpNm(updatedRoomGrp.getRoomGrpNm());
        existingRoomGrp.setRoomGrpUnxTmUpdt(updatedRoomGrp.getRoomGrpUnxTmUpdt());

        roomGrpRepository.save(existingRoomGrp);
    }

    @Override
    public void delete(Long roomGrpKy) {
        roomGrpRepository.deleteById(roomGrpKy);
    }

    @Override
    public List<Room> getChildElements(RoomGrp roomGrp) {
        return roomGrp.getRooms();
    }

    @Override
    public void addChildElement(RoomGrp roomGrp, Room room) {
        List<Room> rooms = roomGrp.getRooms();
        rooms.add(room);
        room.setRoomGrp(roomGrp);
        roomGrpRepository.save(roomGrp);
    }
}
