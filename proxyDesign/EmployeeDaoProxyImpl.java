package proxyDesign;

public class EmployeeDaoProxyImpl implements EmployeeDao{
    EmployeeDao employeeDao;
    @Override
    public void create() {
        if ADMIN:
            employeeDao.create();
        else
            throw exception
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    public EmployeeDaoProxyImpl() {
        employeeDao=new EmployeeDaoImpl();
    }
    
}
