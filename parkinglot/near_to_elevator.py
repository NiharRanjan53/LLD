# Not used now will add later
NearToElevatorParkingStrategy(ParkingStrategy):
    def __init__(self, parking_spot_manager):
        self.parking_spot_manager = parking_spot_manager

    def park_vehicle(self, vehicle):
        return self.parking_spot_manager.park_vehicle(vehicle)

    def remove_vehicle(self, spot_id):
        return self.parking_spot_manager.remove_vehicle(spot_id)