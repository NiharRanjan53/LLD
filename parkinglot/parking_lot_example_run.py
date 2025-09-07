# parking_lot_example_run_fixed.py
from time import sleep
from parking_spot_manager_factory import ParkingSpotManagerFactory
from vehicle import TwoWheeler, FourWheeler
from entrance_gate import EntranceGate
from exit_gate import ExitGate
from minute_pricing_startegy import MinuteWisePricingStrategy
from hourly_pricing_startegy import HourWisePricingStrategy


# Print status for all managers
def print_all_status(managers: dict):
    """Print status for all managers in a dict {label: manager}."""
    print("=" * 50)

    for label, mgr in managers.items():
        print(f"{label} status:", mgr.get_status())
    print("=" * 50)

# ---------------- Setup ----------------
two_mgr = ParkingSpotManagerFactory.get_parking_spot_manager("TwoWheeler", count=2)
four_mgr = ParkingSpotManagerFactory.get_parking_spot_manager("FourWheeler", count=2)
managers = {
    "Two-wheeler": two_mgr,
    "Four-wheeler": four_mgr
}


active_tickets = {}
inactive_tickets = {}

print("Initial two-wheeler status:", two_mgr.get_status())
print("Initial four-wheeler status:", four_mgr.get_status())
print("=" * 80)

# entrance gate
entrance_gate = EntranceGate("EntranceGate1")
print("Entrance gate details:", entrance_gate.get_entrance_gate_details())
print("=" * 80)

# allocate two-wheelers (attempt 3 but only 2 spots initially)
for i in range(3):
    vehicle = TwoWheeler(f"KA-01-KB{i+1}-Two")
    spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
    print("Found spot for two-wheeler:", spot.spot_id if spot else "none")
    ticket = entrance_gate.book_parking_spot(vehicle)
    if ticket:
        active_tickets[ticket.ticket_id] = ticket
        print("Booked ticket:", ticket.get_ticket_details())
    else:
        print("No spot available for two-wheeler")
    # Print status after each attempt
    print_all_status(managers)
    print("-" * 50)

# dynamically add more spots
two_mgr.add_parking_spots(2)
print("Added 2 more two-wheeler spots.")
print("Two-wheeler Spot status:", two_mgr.get_status())
print("-" * 50)

# attempt another two-wheeler
vehicle = TwoWheeler("KA-01-KB4-Two")
spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
print("Found spot for two-wheeler:", spot.spot_id if spot else "none")
ticket = entrance_gate.book_parking_spot(vehicle)
if ticket:
    active_tickets[ticket.ticket_id] = ticket
    print("Booked ticket:", ticket.get_ticket_details())
else:
    print("No spot available for two-wheeler")

# Print final status
print_all_status(managers)
print("=" * 80)

# allocate four-wheelers (attempt 3 but only 2 spots initially)
for i in range(3):
    vehicle = FourWheeler(f"KA-01-KB{i+1}-Four")
    spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
    print("Found spot for four-wheeler:", spot.spot_id if spot else "none")
    ticket = entrance_gate.book_parking_spot(vehicle)
    if ticket:
        active_tickets[ticket.ticket_id] = ticket
        print("Booked ticket:", ticket.get_ticket_details())
    else:
        print("No spot available for four-wheeler")
    # Print status after each attempt
    print_all_status(managers)
    print("-" * 50)

four_mgr.add_parking_spots(2)
print("Added 2 more four-wheeler spots.")
print("Four-wheeler Spot status:", four_mgr.get_status())
print("-" * 50)

vehicle = FourWheeler("KA-01-KB4-Four")
spot = entrance_gate.find_parking_spot(vehicle.vehicle_type)
print("Found spot for four-wheeler:", spot.spot_id if spot else "none")
ticket = entrance_gate.book_parking_spot(vehicle)
if ticket:
    active_tickets[ticket.ticket_id] = ticket
    print("Booked ticket:", ticket.get_ticket_details())
else:
    print("No spot available for four-wheeler")

# Print final status
print_all_status(managers)
print("=" * 80)

sleep(2)  # simulate parking duration

# ---------------- Exit Gate ----------------
print("================= Exit Gate Operations ===============")
exit_gate = ExitGate("ExitGate1")
print("Exit gate details:", exit_gate.get_exit_gate_details())
print("-" * 50)

# pricing strategies
minute_pricing = MinuteWisePricingStrategy(rate_per_minute=2)
hourly_pricing = HourWisePricingStrategy(rate_per_hour=50)

# iterate safely over snapshot of active tickets
ticket_ids = list(active_tickets.keys())
# for ticket_id in ticket_ids:
#     print("ticket_id:", ticket_id)
for ticket_id in ticket_ids:
    ticket = active_tickets[ticket_id]
    print("Processing exit for:", ticket_id, ticket.get_ticket_details())

    # choose pricing based on vehicle type string
    pricing = minute_pricing if ticket.vehicle.vehicle_type == "TwoWheeler" else hourly_pricing

    exit_details = exit_gate.process_exit(ticket, pricing)

    # process_exit returns a dict (adjust according to your implementation)
    # print the returned structure - adapt printing to your object's API
    print("Exit details:", exit_details)

    # free spot: if your process_exit didn't free, call manager free explicitly
    # e.g., two_mgr.remove_vehicle(ticket.spot_id) or four_mgr.remove_vehicle(...)

    # remove from active tickets (safe because we iterated over snapshot)
    active_tickets.pop(ticket_id, None)
    print("Exit Processed:", ticket_id, ticket.get_ticket_details())
    print(f"Ticket {ticket_id} removed from active tickets.")
    
    print_all_status(managers)
    print("-" * 50)


