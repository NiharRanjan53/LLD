from cost_computation_factory import CostComputationFactory
from parking_spot_manager_factory import ParkingSpotManagerFactory
from cost_computation import CostComputation
from pricing_startegy import PricingStrategy
from ticket import Ticket


class ExitGate:
    def __init__(self, gate_id):
        self.gate_id = gate_id
        self.parking_spot_manager = None
        self.ticket: Ticket = None
        self.cost_computation: CostComputation = None

    def process_exit(self, ticket: Ticket, pricing_strategy: PricingStrategy) -> Ticket:
        # Assign the parking spot manager and cost computation based on vehicle type
        self.parking_spot_manager = ParkingSpotManagerFactory.get_parking_spot_manager(ticket.vehicle_type)
        self.cost_computation = CostComputationFactory.get_cost_computation(ticket.vehicle_type, pricing_strategy)

        # Compute the cost and close the ticket
        cost = self.cost_computation.compute_cost(ticket)
        print(f"Exit processed for Ticket ID: {ticket.ticket_id}, Total Cost: {cost}")

        # Free the parking spot and process payment
        self.parking_spot_manager.remove_vehicle(ticket.spot_id)

        # Process payment
        payment_method = "card"  # Example payment method
        payment_status = self.process_payment(cost, payment_method)
        if payment_status:
            ticket.paid = True  
        else:  
            print("Payment failed. Cannot exit.")
            return None
        return ticket
    def process_payment(self, amount: float, payment_method: str) -> bool:
        """Will implement the diffrent payment methods like cash, card, upi etc. later
        Args:
            amount (float): The amount to be paid.
            payment_method (str): The method of payment (e.g., "cash", "card", "upi").
        """
        # Simulate payment processing
        print(f"Processing payment of {amount} via {payment_method}")
        return True # Assume payment is always successful for this example

    def get_exit_gate_details(self):
        return {
            "gate_id": self.gate_id,
            "current_ticket": self.ticket.get_ticket_details() if self.ticket else None,
            "cost_computation_strategy": self.cost_computation.pricing_strategy.__class__.__name__ if self.cost_computation else None
        }