from datetime import datetime
from cost_computation import CostComputation
from pricing_startegy import PricingStrategy
from ticket import Ticket

class FourWheelerCostComputation(CostComputation):
    def __init__(self, pricing_strategy: PricingStrategy):
        super().__init__(pricing_strategy)

    def compute_cost(self, ticket: Ticket) -> float:
        exit_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        
        # Convert entry and exit times from string to datetime
        entry_dt = datetime.strptime(ticket.entry_time, "%Y-%m-%d %H:%M:%S")
        exit_dt = datetime.strptime(exit_time, "%Y-%m-%d %H:%M:%S")
        duration = (exit_dt - entry_dt).total_seconds()
        price = self.pricing_strategy.calculate_fee(duration)
        ticket.close_ticket(exit_time, duration, price)
        print(f"Total parking duration (seconds): {duration}, Price: {price}, Ticket ID: {ticket.ticket_id}, pricing strategy: {self.pricing_strategy.__class__.__name__}")
        return price

    def generate_bill(self, ticket: Ticket) -> str:
        return self.pricing_strategy.generate_bill(ticket)