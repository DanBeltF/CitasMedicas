/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.simpleview;

import edu.eci.pdsw.persistence.impl.mappers.EpsMapper;
import edu.eci.pdsw.persistence.impl.mappers.PacienteMapper;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Eps;
import edu.eci.pdsw.samples.entities.Paciente;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBATISExample {

/**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage(),e);
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejemplo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        PacienteMapper pmapper=sqlss.getMapper(PacienteMapper.class);
        
        //imprimir todos los nombres de los pacientes
        //List<Paciente> pacientes=pmapper.loadPacientes();
        //for (Paciente p:pacientes){
            //System.out.println(p.getNombre());}
        
        //obtener paciente por id
        //Paciente paciente =pmapper.loadPacienteById(1026585446, "CC");
        //System.out.println(paciente.getNombre());
        
        //insertar paciente en la BD
        //Paciente b= new Paciente(55555,"CC", "testy", new Date(2001, 01, 01), new Eps("Coomeva","8456984"));
        //registrarNuevoPaciente(pmapper, b);
        
        //actualizar paciente
        //Paciente paciente = pmapper.loadPacienteById(55555, "CC");
        //actualizarPaciente(pmapper, paciente);
        //sqlss.commit(); 
        
        //imprimir nombres eps
        //EpsMapper emapper=sqlss.getMapper(EpsMapper.class);
        //List<Eps> epss=emapper.loadAllEps();
        //for(Eps e:epss){
        //    System.out.println(e.getNombre());}
        
}

    /**
     * Registra un nuevo paciente y sus respectivas consultas (si existiesen).
     * @param pmap mapper a traves del cual se hará la operacion
     * @param p paciente a ser registrado
     */
    public static void registrarNuevoPaciente(PacienteMapper pmap, Paciente p){
        pmap.insertarPaciente(p);
        Consulta con = new Consulta(new Date(2017, 10, 12), "Problema mental", 70000);
        Consulta con2 = new Consulta(new Date(2017, 10, 13), "Problema cardiaco", 60000);
        pmap.insertConsulta(con, p.getId(), p.getTipoId(), 70000);
        pmap.insertConsulta(con2, p.getId(), p.getTipoId(), 60000);
    }
    
    /**
     * @obj Actualizar los datos básicos del paciente, con sus * respectivas
     * consultas.
     * @pre El paciente p ya existe
     * @param pmap mapper a traves del cual se hará la operacion
     * @param p paciente a ser registrado
     */
    public static void actualizarPaciente(PacienteMapper pmap, Paciente p){
        pmap.actualizarPaciente(p);
    }
    
}
