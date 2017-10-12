/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.impl.mappers;

import java.util.List;
import edu.eci.pdsw.samples.entities.Eps;

/**
 *
 * @author 2104784
 */
public interface EpsMapper {
    public List<Eps> loadAllEps();
}
