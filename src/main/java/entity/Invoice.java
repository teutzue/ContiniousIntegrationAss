package entity;

import java.util.Date;
import java.util.List;

public class Invoice
{
    private int id;
    private int userId;
    private Date created;
    private List<InvoiceLine> lines;
    private boolean paid;

    public Invoice(int id, int userId, Date created, List<InvoiceLine> lines)
    {
        this.id = id;
        this.userId = userId;
        this.created = created;
        this.lines = lines;
    }

    public Invoice(int userId)
    {
        this.userId = userId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public List<InvoiceLine> getLines()
    {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines)
    {
        this.lines = lines;
    }

    public void addLine(InvoiceLine line)
    {
        this.lines.add(line);
    }

    public boolean isPaid()
    {
        return paid;
    }

    public void setPaid(boolean paid)
    {
        this.paid = paid;
    }
}
