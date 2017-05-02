package entity;

public class InvoiceLine
{
    private int id;
    private int invoiceId;
    private Cake cupcake;
    private int amount;
    private double price;

    public InvoiceLine(int id, int invoiceId, Cake cupcake, int amount)
    {
        this.id = id;
        this.invoiceId = invoiceId;
        this.cupcake = cupcake;
        this.amount = amount;
    }

    public int getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId)
    {
        this.invoiceId = invoiceId;
    }

    public Cake getCupcake()
    {
        return cupcake;
    }

    public void setCupcake(Cake cupcake)
    {
        this.cupcake = cupcake;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public int getId()
    {
        return id;
    }

    public double getPrice()
    {
        Cake cc = this.cupcake;
        return (cc.getBottom().getPrice() + cc.getTop().getPrice()) * this.amount;
    }
}