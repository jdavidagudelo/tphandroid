package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.AntecedenteDto;
import com.artica.telesalud.tph.android.lightweightmodel.AntecedentesDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 5/09/14.
 */
public class AntecedentesService {
    private DatabaseHelper helper;
    private Dao<AntecedenteDto, Integer> antecedenteDao;
    private Dao<AntecedentesDto, Integer> antecedentesDao;
    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        antecedenteDao = helper.getAntecedenteDao();
        antecedentesDao = helper.getAntecedentesDao();
    }
    public AntecedentesService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public AntecedentesDto save(AntecedentesDto antecedentes) throws SQLException {
        if(antecedentes != null) {
            antecedentesDao.createOrUpdate(antecedentes);
            for(AntecedenteDto a : antecedentes.getAntecedentes())
            {
                a.setAntecedentesId(antecedentes.getId());
                antecedenteDao.createOrUpdate(a);
            }
        }
        return antecedentes;
    }
}
