package gal.san.clemente.tarefa_5_bdo.model.dao.utils;

public class ModelQuery {
    //Consultas para Clientes
    public static final String INSERT_CLIENTE = "INSERT INTO clientes(nome, apelidos, email) VALUES(?, ?, ?)";
    public static final String UPDATE_CLIENTE = "UPDATE clientes SET nome = ?, apelidos = ?, email = ? where id = ?";
    public static final String DELETE_CLIENTE = "DELETE FROM clientes WHERE id = ?";
    public static final String GET_ALL_CLIENTES = "SELECT id, nome, apelidos, email FROM clientes";
    public static final String GET_ONE_CLIENTE = "SELECT id, nome, apelidos, email FROM clientes WHERE id = ?";
    
    //Consultas para Productos
    public static final String INSERT_PRODUCT = "INSERT INTO productos(nome, descripcion, prezo) VALUES(?, ?, ?)";
    public static final String UPDATE_PRODUCT = "UPDATE productos SET nome = ?, descripcion = ?, prezo = ? where id = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM productos WHERE id = ?";
    public static final String GET_ALL_PRODUCTS = "SELECT id, nome, descripcion, prezo FROM productos";
    public static final String GET_ONE_PRODUCT = "SELECT id, nome, descripcion, prezo FROM productos WHERE id = ?";
    
    //Consultas para Tendas
    public static final String INSERT_TENDA = "INSERT INTO tendas(nome, cidade, provincia) VALUES(?, ?, ?)";
    public static final String UPDATE_TENDA = "UPDATE tendas SET nome = ?, cidade = ?, provincia = ? where id = ?";
    public static final String DELETE_TENDA = "DELETE FROM tendas WHERE id = ?";
    public static final String GET_ALL_TENDA = "SELECT id, nome, cidade, provincia FROM tendas";
    public static final String GET_ONE_TENDA = "SELECT id, nome, cidade, provincia FROM tendas WHERE id = ?";
    
    //Consultas para Empleados
    public static final String INSERT_EMPLEADO = "INSERT INTO empleados(nome, apelidos) VALUES(?, ?)";
    public static final String UPDATE_EMPLEADO = "UPDATE empleados SET nome = ?, apelidos = ? where id = ?";
    public static final String DELETE_EMPLEADO = "DELETE FROM empleados WHERE id = ?";
    public static final String GET_ALL_EMPLEADO = "SELECT id, nome, apelidos FROM empleados";
    public static final String GET_ONE_EMPLEADO = "SELECT id, nome, apelidos FROM empleados WHERE id = ?";
    
    //Consultas para tendas_productos
    public static final String INSERT_TENDA_PRODUCTO = "INSERT INTO tendas_productos(tenda, producto, stock) VALUES(?, ?, ?)";
    public static final String UPDATE_TENDA_PRODUCTO = "UPDATE tendas_productos SET tenda = ?, producto = ?, stock = ? where id = ?";
    public static final String DELETE_TENDA_PRODUCTO = "DELETE FROM tendas_productos WHERE id = ?";
    public static final String GET_ALL_TENDA_PRODUCTO = "SELECT id, tenda, producto, stock FROM tendas_productos";
    public static final String GET_ONE_TENDA_PRODUCTO = "SELECT id, tenda, producto, stock FROM tendas_productos WHERE id = ?";
    public static final String GET_ALL_TENDA_PRODUCTO_POR_TENDA = "SELECT id, tenda, producto, stock FROM tendas_productos where tenda = ?";
    
    //Consultas para tendas_productos
    public static final String INSERT_TENDA_EMPLEADO = "INSERT INTO tendas_empregados(tenda, empregado, numero_horas_semana) VALUES(?, ?, ?)";
    public static final String UPDATE_TENDA_EMPLEADO = "UPDATE tendas_empregados SET tenda = ?, empregado = ?, numero_horas_semana = ? where id = ?";
    public static final String DELETE_TENDA_EMPLEADO = "DELETE FROM tendas_empregados WHERE id = ?";
    public static final String GET_ALL_TENDA_EMPLEADO = "SELECT id, tenda, empregado, numero_horas_semana FROM tendas_empregados";
    public static final String GET_ONE_TENDA_EMPLEADO = "SELECT id, tenda, empregado, numero_horas_semana FROM tendas_empregados WHERE id = ?";
    public static final String GET_ALL_TENDA_EMPREGADO_POR_TENDA = "SELECT id, tenda, empregado, numero_horas_semana FROM tendas_empregados where tenda = ?";
    
}
