package iss4u.ehr.backoffice.parameterization.material_resources.services;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.Equipment;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    void create(Room room);

    List<Room> retrieveRooms();

    Optional<Room> getRoomByKy(Long roomKy);

    Room getRoomByNm(String roomLabel);

    String getRoomNameByKey(Long roomKy);

    void update(Long roomKy, Room updatedRoom);

    void delete(Long RoomKy);

    List<Equipment> getChildElements(Room room);

    void addChildElement(Room room, Equipment equipment);
}
