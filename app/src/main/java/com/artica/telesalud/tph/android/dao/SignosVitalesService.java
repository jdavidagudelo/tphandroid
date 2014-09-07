package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.SignosVitalesDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 25/08/14.
 */
public class SignosVitalesService {
    private Dao<SignosVitalesDto, Integer> signosVitalesDao;
    private EncounterService encounterService;
    private DatabaseHelper helper;
    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        signosVitalesDao = helper.getSignosVitalesDao();
        encounterService = new EncounterService(helper);
    }
    public SignosVitalesService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized SignosVitalesDto save(SignosVitalesDto signosVitalesDto) throws SQLException {
        signosVitalesDao.createOrUpdate(signosVitalesDto);
        return signosVitalesDto;
    }
    public void fillSignoVital(SignosVitalesDto signosVitales) throws SQLException {
        if(signosVitales != null)
        {
            if(signosVitales.getEncounter() != null) {
                if (signosVitales.getEncounter().getEncounterId() != null) {
                    signosVitales.setEncounter(encounterService.getEncounter(signosVitales.getEncounter().getEncounterId()));
                } else if (signosVitales.getEncounter().getId() != null) {
                    signosVitales.setEncounter(encounterService.getEncounterById(signosVitales.getEncounter().getId()));
                }
                else if(signosVitales.getEncounterId() != null) {
                    signosVitales.setEncounter(encounterService.getEncounterById(signosVitales.getEncounterId()));
                }
            }
            else if(signosVitales.getEncounterId() != null) {
                signosVitales.setEncounter(encounterService.getEncounterById(signosVitales.getEncounterId()));
            }
        }
    }
    public List<SignosVitalesDto> getSignosVitalesByEncounter(Integer encounterId) throws SQLException {
        List<SignosVitalesDto> signosVitales = signosVitalesDao.queryBuilder().where().eq("encounterId", encounterId).query();
        for(SignosVitalesDto signoVital : signosVitales)
        {
            fillSignoVital(signoVital);
        }
        return signosVitales;
    }
}
