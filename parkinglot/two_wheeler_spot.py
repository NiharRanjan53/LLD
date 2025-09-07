from parking_spot import ParkingSpot
class TwoWheelerSpot(ParkingSpot):
    def __init__(self, spot_id):
        super().__init__(spot_id, "TwoWheeler")