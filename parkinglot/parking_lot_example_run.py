# Example run file to demonstrate parking lot functionality
from parkinglot.parking_spot_manager_factory import ParkingSpotManagerFactory
from parkinglot.vehicle import TwoWheeler, FourWheeler
from parkinglot.entrance_gate import EntranceGate

# create singleton managers (first call creates)
two_mgr = ParkingSpotManagerFactory.get_parking_spot_manager("two", count=2)
four_mgr = ParkingSpotManagerFactory.get_parking_spot_manager("four", count=2)

print("Two-wheeler status:", two_mgr.get_status())
print("Four-wheeler status:", four_mgr.get_status())

print("= " * 40)

# create an entrance gate
entrance_gate = EntranceGate("Gate1")
print("Entrance gate details:", entrance_gate.get_entrance_gate_details())

print("= " * 40)

# allocate a two-wheeler via entrance gate
for i in range(3):  # Trying to allocate 3 two-wheelers, but only 2 spots available
    vehicle = TwoWheeler(f"KA-01-000{i+1}")
    # entrance_gate.parking_spot_manager = entrance_gate.get_parking_spot_manager(vehicle.vehicle_type)
    spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
    print("Found spot for two-wheeler:", spot.spot_id if spot else "none")
    ticket = entrance_gate.book_parking_spot(vehicle)

    if ticket:
        print("Booked ticket:", ticket.get_ticket_details())
    else:
        print("No spot available for two-wheeler")

    print("Two-wheeler Spot status:", two_mgr.get_status())
    print("Four-wheeler Spot status:", four_mgr.get_status())
    print("*" * 50)

two_mgr.add_parking_spots(2)  # Add 2 more spots
print("Added 2 more two-wheeler spots.")
print("Two-wheeler Spot status:", two_mgr.get_status())


vehicle = TwoWheeler(f"KA-01-0004")
spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
print("Found spot for two-wheeler:", spot.spot_id if spot else "none")
ticket = entrance_gate.book_parking_spot(vehicle)

if ticket:
    print("Booked ticket:", ticket.get_ticket_details())
else:
    print("No spot available for two-wheeler")

print("Two-wheeler status:", two_mgr.get_status())
print("Four-wheeler status:", four_mgr.get_status())
print("*" * 40)

print("=" * 60)

# allocate a four-wheeler via entrance gate
for i in range(3):  # Trying to allocate 3 four-wheelers, but only 2 spots available
    vehicle = FourWheeler(f"KA-01-000{i+1}")
    spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
    print("Found spot for four-wheeler:", spot.spot_id if spot else "none")
    ticket = entrance_gate.book_parking_spot(vehicle)

    if ticket:
        print("Booked ticket:", ticket.get_ticket_details())
    else:
        print("No spot available for four-wheeler")

    print("Two-wheeler Spot status:", two_mgr.get_status())
    print("Four-wheeler Spot status:", four_mgr.get_status())
    print("*" * 50)

four_mgr.add_parking_spots(2)  # Add 2 more spots
print("Added 2 more four-wheeler spots.")
print("Four-wheeler Spot status:", four_mgr.get_status())


vehicle = FourWheeler(f"KA-01-0004")
spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
print("Found spot for four-wheeler:", spot.spot_id if spot else "none")
ticket = entrance_gate.book_parking_spot(vehicle)

if ticket:
    print("Booked ticket:", ticket.get_ticket_details())
else:
    print("No spot available for two-wheeler")

print("Two-wheeler status:", two_mgr.get_status())
print("Four-wheeler status:", four_mgr.get_status())
print("*" * 40)
# # allocate a two-wheeler
# v1 = TwoWheeler("KA-01-0001")
# spot = two_mgr.park_vehicle(v1)
# print("Allocated two-wheeler to spot:", spot.spot_id if spot else "none")

# # allocate a two-wheeler
# v2 = TwoWheeler("KA-01-0001")
# spot = two_mgr.park_vehicle(v2)
# print("Allocated two-wheeler to spot:", spot.spot_id if spot else "none")

# # allocate a two-wheeler
# v2 = TwoWheeler("KA-01-0001")
# spot = two_mgr.park_vehicle(v2)
# print("Allocated two-wheeler to spot:", spot.spot_id if spot else "none")

# # try another two-wheeler
# v3 = TwoWheeler("KA-01-0002")
# spot = two_mgr.park_vehicle(v3)
# if not spot:
#     print("No spot available for two-wheeler")
# else:
#     print("Allocated two-wheeler to spot:", spot.spot_id if spot else "none")   


# # free a spot
# if spot:
#     ok = two_mgr.remove_vehicle(spot.spot_id)
#     print("Freed spot", spot.spot_id, ok)

# print("Two-wheeler status:", two_mgr.get_status())
# print("Four-wheeler status:", four_mgr.get_status())


# # create singleton managers (first call creates)
# two_mgr.add_parking_spots(10)

# # free a spot
# if spot:
#     ok = two_mgr.remove_vehicle(spot.spot_id)
#     print("Freed spot", spot.spot_id, ok)

# print("Two-wheeler status:", two_mgr.get_status())
# print("Four-wheeler status:", four_mgr.get_status())
