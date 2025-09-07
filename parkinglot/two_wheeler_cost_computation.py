from datetime import datetime
from pricing_startegy import PricingStrategy
from cost_computation import CostComputation
from ticket import Ticket

class TwoWheelerCostComputation(CostComputation):
    """Concrete implementation of CostComputation for two-wheelers.
    Uses the provided PricingStrategy to compute costs and generate bills.
    
    Attributes:
        pricing_strategy (PricingStrategy): The strategy used for pricing calculations.

        Methods:
            __init__(self, pricing_strategy: PricingStrategy)
            compute_cost(self, ticket: Ticket) -> float
            generate_bill(self, ticket: Ticket) -> str
    """
    def __init__(self, pricing_strategy: PricingStrategy):
        super().__init__(pricing_strategy)

    def compute_cost(self, ticket: Ticket) -> float:
        """Compute the parking cost for a two-wheeler based on the ticket details.
        Args:
            ticket (Ticket): The parking ticket for which to compute the cost.
        Returns:
            float: The computed parking cost.
        """
        exit_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        entry_dt = datetime.strptime(ticket.entry_time, "%Y-%m-%d %H:%M:%S")
        exit_dt = datetime.strptime(exit_time, "%Y-%m-%d %H:%M:%S")
        duration = (exit_dt - entry_dt).total_seconds()
        price = self.pricing_strategy.calculate_fee(duration)
        ticket.close_ticket(exit_time, duration, price)
        print(f"Total parking duration (seconds): {duration}, Price: {price}, Ticket ID: {ticket.ticket_id}, pricing strategy: {self.pricing_strategy.__class__.__name__}")
        return price

    def generate_bill(self, ticket: Ticket) -> str:
        return self.pricing_strategy.generate_bill(ticket)
