package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public final class GoldCustomer extends AbstractCustomer {

    private LocalDate lastOrderDate;

    public GoldCustomer(String id, String name,
                        int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);
        this.lastOrderDate = lastOrderDate;

    }


    @Override
    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() >= 100) {
            bonusPoints += 1.5 * order.getTotal();
        }
    }

    // TODO: Maybe take methods from super class?

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        AbstractCustomer other = (AbstractCustomer) obj;

        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(bonusPoints, other.bonusPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bonusPoints);
    }

    @Override
    public String asString() {
        return "Gold customer " + name + " with " + bonusPoints + " points";
    }

}