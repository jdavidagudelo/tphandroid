package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.ProcedimientosSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by interoperabilidad on 29/07/14.
 */
@DatabaseTable()
public class ProcedimientosDto implements Serializable, Parcelable {
    @DatabaseField()
    private Boolean aspiracionSecreciones = false;
    @DatabaseField()
    private Boolean canulaOrofaringea = false;
    @DatabaseField()
    private Boolean despejeManual = false;
    @DatabaseField()
    private Boolean collarCervical = false;
    @DatabaseField()
    private Boolean canulaNasofaringea = false;
    @DatabaseField()
    private Boolean reanimacionRespiratoria = false;
    @DatabaseField()
    private Boolean canulaNasal = false;
    @DatabaseField()
    private Boolean mascaraNoReinhalacion = false;
    @DatabaseField()
    private Boolean mascaraSimple = false;
    @DatabaseField()
    private Boolean bvm = false;
    @DatabaseField()
    private Boolean rccp = false;
    @DatabaseField()
    private Boolean hemostasia = false;
    @DatabaseField()
    private Boolean dea = false;
    @DatabaseField()
    private Boolean monitoreoSignosVitales = false;
    @DatabaseField()
    private Boolean dispositivoSupragliotico = false;
    @DatabaseField()
    private Boolean cricotiroidotomia = false;
    @DatabaseField()
    private Boolean intubacionOrotraqueal = false;
    @DatabaseField()
    private Boolean descompresionTorax = false;
    @DatabaseField()
    private Boolean ventilacionMecanica = false;
    @DatabaseField()
    private Boolean rehidratacionOral = false;
    @DatabaseField()
    private Boolean ssn09 = false;
    @DatabaseField()
    private Boolean accesoVenosoPeriferico = false;
    @DatabaseField()
    private Boolean destrosa = false;
    @DatabaseField()
    private Boolean punsionOsea = false;
    @DatabaseField()
    private Boolean coloides = false;
    @DatabaseField()
    private Boolean hartman = false;
    @DatabaseField()
    private Boolean exposicion = false;
    @DatabaseField()
    private Boolean movimientoBloque = false;
    @DatabaseField()
    private Boolean lavadoCuracion = false;
    @DatabaseField()
    private Boolean chalecoExtracionVehicular = false;
    @DatabaseField()
    private Boolean mantaTermica = false;
    @DatabaseField()
    private Boolean tablaEspinalLarga = false;
    @DatabaseField()
    private Boolean inmovilizacionFerulas = false;
    @DatabaseField()
    private Boolean tablaEspinalCorta = false;
    @DatabaseField()
    private String otrosProcedimientos;
    private EvaluacionCompletaDto evaluacion;
    @DatabaseField()
    private Integer evaluacionId;
    @DatabaseField()
    private Boolean masajeCardiaco = Boolean.FALSE;
    @DatabaseField()
    private Boolean accesoVenosoCentral = Boolean.FALSE;
    @DatabaseField()
    private Boolean puncionOsea = Boolean.FALSE;
    @DatabaseField(generatedId = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static final Parcelable.Creator<ProcedimientosDto> CREATOR = new Parcelable.Creator<ProcedimientosDto>() {
        public ProcedimientosDto createFromParcel(Parcel in) {
            return new ProcedimientosDto(in);
        }

        public ProcedimientosDto[] newArray(int size) {
            return new ProcedimientosDto[size];
        }
    };
    public ProcedimientosDto(){

    }

    public Boolean getPuncionOsea() {
        return puncionOsea;
    }

    public void setPuncionOsea(Boolean puncionOsea) {
        this.puncionOsea = puncionOsea;
    }

    public Boolean getAccesoVenosoCentral() {
        return accesoVenosoCentral;
    }

    public void setAccesoVenosoCentral(Boolean accesoVenosoCentral) {
        this.accesoVenosoCentral = accesoVenosoCentral;
    }

    public ProcedimientosDto(ProcedimientosSpringDto procedimientosDto)
    {
        aspiracionSecreciones =procedimientosDto.getAspiracionSecreciones();
        canulaOrofaringea =procedimientosDto.getCanulaOrofaringea();
        despejeManual =procedimientosDto.getDespejeManual();
        collarCervical =procedimientosDto.getCollarCervical();
        canulaNasofaringea =procedimientosDto.getCanulaNasofaringea();
        reanimacionRespiratoria =procedimientosDto.getReanimacionRespiratoria();
        canulaNasal =procedimientosDto.getCanulaNasal();
        mascaraNoReinhalacion =procedimientosDto.getMascaraNoReinhalacion();
        mascaraSimple =procedimientosDto.getMascaraSimple();
        bvm =procedimientosDto.getBvm();
        rccp =procedimientosDto.getRccp();
        hemostasia =procedimientosDto.getHemostasia();
        dea =procedimientosDto.getDea();
        monitoreoSignosVitales =procedimientosDto.getMonitoreoSignosVitales();
        dispositivoSupragliotico =procedimientosDto.getDispositivoSupragliotico();
        cricotiroidotomia =procedimientosDto.getCricotiroidotomia();
        intubacionOrotraqueal =procedimientosDto.getIntubacionOrotraqueal();
        descompresionTorax =procedimientosDto.getDescompresionTorax();
        ventilacionMecanica =procedimientosDto.getVentilacionMecanica();
        rehidratacionOral =procedimientosDto.getRehidratacionOral();
        ssn09 =procedimientosDto.getSsn09();
        accesoVenosoPeriferico =procedimientosDto.getAccesoVenosoPeriferico();
        destrosa =procedimientosDto.getDestrosa();
        punsionOsea =procedimientosDto.getPunsionOsea();
        coloides =procedimientosDto.getColoides();
        hartman =procedimientosDto.getHartman();
        exposicion =procedimientosDto.getExposicion();
        movimientoBloque =procedimientosDto.getMovimientoBloque();
        lavadoCuracion =procedimientosDto.getLavadoCuracion() ;
        chalecoExtracionVehicular =procedimientosDto.getChalecoExtracionVehicular();
        mantaTermica =procedimientosDto.getMantaTermica();
        tablaEspinalLarga =procedimientosDto.getTablaEspinalLarga();
        inmovilizacionFerulas =procedimientosDto.getInmovilizacionFerulas();
        tablaEspinalCorta =procedimientosDto.getTablaEspinalCorta();
        otrosProcedimientos=procedimientosDto.getOtrosProcedimientos();
        if(procedimientosDto.getEvaluacion() != null) {
            evaluacion = new EvaluacionCompletaDto(procedimientosDto.getEvaluacion());
        }
        masajeCardiaco=procedimientosDto.getMasajeCardiaco();
        accesoVenosoCentral=procedimientosDto.getAccesoVenosoCentral();
        puncionOsea =procedimientosDto.getPuncionOsea();

    }

    public ProcedimientosDto(Parcel parcel) {
        id=(Integer)parcel.readValue(null);
        aspiracionSecreciones = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        canulaOrofaringea = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        despejeManual = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        collarCervical = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        canulaNasofaringea =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        reanimacionRespiratoria = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        canulaNasal = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        mascaraNoReinhalacion = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        mascaraSimple = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        bvm = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        rccp =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        hemostasia =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        dea = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        monitoreoSignosVitales = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        dispositivoSupragliotico = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        cricotiroidotomia = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        intubacionOrotraqueal = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        descompresionTorax =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        ventilacionMecanica = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        rehidratacionOral =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        ssn09 = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        accesoVenosoPeriferico = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        accesoVenosoCentral = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        destrosa =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        punsionOsea = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        coloides =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        hartman =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        exposicion = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        movimientoBloque =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        lavadoCuracion =(Boolean)parcel.readValue(Boolean.class.getClassLoader());
        chalecoExtracionVehicular = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        mantaTermica = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        tablaEspinalLarga = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        inmovilizacionFerulas = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        tablaEspinalCorta = (Boolean)parcel.readValue(Boolean.class.getClassLoader());
        otrosProcedimientos = (String)parcel.readValue(null);
        evaluacion = (EvaluacionCompletaDto)parcel.readValue(EvaluacionCompletaDto.class.getClassLoader());
        evaluacionId = (Integer)parcel.readValue(null);
        masajeCardiaco = (Boolean)parcel.readValue(null);
        puncionOsea = (Boolean)parcel.readValue(null);
    }

    public Boolean getMasajeCardiaco() {
        return masajeCardiaco;
    }

    public void setMasajeCardiaco(Boolean masajeCardiaco) {
        this.masajeCardiaco = masajeCardiaco;
    }

    public Boolean getAspiracionSecreciones() {
        return aspiracionSecreciones;
    }

    public void setAspiracionSecreciones(Boolean aspiracionSecreciones) {
        this.aspiracionSecreciones = aspiracionSecreciones;
    }

    public Boolean getCanulaOrofaringea() {
        return canulaOrofaringea;
    }

    public void setCanulaOrofaringea(Boolean canulaOrofaringea) {
        this.canulaOrofaringea = canulaOrofaringea;
    }

    public Boolean getDespejeManual() {
        return despejeManual;
    }

    public void setDespejeManual(Boolean despejeManual) {
        this.despejeManual = despejeManual;
    }

    public Boolean getCollarCervical() {
        return collarCervical;
    }

    public void setCollarCervical(Boolean collarCervical) {
        this.collarCervical = collarCervical;
    }

    public Boolean getCanulaNasofaringea() {
        return canulaNasofaringea;
    }

    public void setCanulaNasofaringea(Boolean canulaNasofaringea) {
        this.canulaNasofaringea = canulaNasofaringea;
    }

     public Boolean getReanimacionRespiratoria() {
        return reanimacionRespiratoria;
    }

    public void setReanimacionRespiratoria(Boolean reanimacionRespiratoria) {
        this.reanimacionRespiratoria = reanimacionRespiratoria;
    }

    public Boolean getCanulaNasal() {
        return canulaNasal;
    }

    public void setCanulaNasal(Boolean canulaNasal) {
        this.canulaNasal = canulaNasal;
    }

    public Boolean getMascaraNoReinhalacion() {
        return mascaraNoReinhalacion;
    }

    public void setMascaraNoReinhalacion(Boolean mascaraNoReinhalacion) {
        this.mascaraNoReinhalacion = mascaraNoReinhalacion;
    }

    public Boolean getMascaraSimple() {
        return mascaraSimple;
    }

    public void setMascaraSimple(Boolean mascaraSimple) {
        this.mascaraSimple = mascaraSimple;
    }

    public Boolean getBvm() {
        return bvm;
    }

    public void setBvm(Boolean bvm) {
        this.bvm = bvm;
    }


    public Boolean getRccp() {
        return rccp;
    }

    public void setRccp(Boolean rccp) {
        this.rccp = rccp;
    }

    public Boolean getHemostasia() {
        return hemostasia;
    }

    public void setHemostasia(Boolean hemostasia) {
        this.hemostasia = hemostasia;
    }

    public Boolean getDea() {
        return dea;
    }

    public void setDea(Boolean dea) {
        this.dea = dea;
    }

    public Boolean getMonitoreoSignosVitales() {
        return monitoreoSignosVitales;
    }

    public void setMonitoreoSignosVitales(Boolean monitoreoSignosVitales) {
        this.monitoreoSignosVitales = monitoreoSignosVitales;
    }

    public Boolean getDispositivoSupragliotico() {
        return dispositivoSupragliotico;
    }

    public void setDispositivoSupragliotico(Boolean dispositivoSupragliotico) {
        this.dispositivoSupragliotico = dispositivoSupragliotico;
    }

    public Boolean getCricotiroidotomia() {
        return cricotiroidotomia;
    }

    public void setCricotiroidotomia(Boolean cricotiroidotomia) {
        this.cricotiroidotomia = cricotiroidotomia;
    }

    public Boolean getIntubacionOrotraqueal() {
        return intubacionOrotraqueal;
    }

    public void setIntubacionOrotraqueal(Boolean intubacionOrotraqueal) {
        this.intubacionOrotraqueal = intubacionOrotraqueal;
    }

    public Boolean getDescompresionTorax() {
        return descompresionTorax;
    }

    public void setDescompresionTorax(Boolean descompresionTorax) {
        this.descompresionTorax = descompresionTorax;
    }

    public Boolean getVentilacionMecanica() {
        return ventilacionMecanica;
    }

    public void setVentilacionMecanica(Boolean ventilacionMecanica) {
        this.ventilacionMecanica = ventilacionMecanica;
    }

    public Boolean getRehidratacionOral() {
        return rehidratacionOral;
    }

    public void setRehidratacionOral(Boolean rehidratacionOral) {
        this.rehidratacionOral = rehidratacionOral;
    }

    public Boolean getSsn09() {
        return ssn09;
    }

    public void setSsn09(Boolean ssn09) {
        this.ssn09 = ssn09;
    }

    public Boolean getAccesoVenosoPeriferico() {
        return accesoVenosoPeriferico;
    }

    public void setAccesoVenosoPeriferico(Boolean accesoVenosoPeriferico) {
        this.accesoVenosoPeriferico = accesoVenosoPeriferico;
    }

    public Boolean getDestrosa() {
        return destrosa;
    }

    public void setDestrosa(Boolean destrosa) {
        this.destrosa = destrosa;
    }

    public Boolean getPunsionOsea() {
        return punsionOsea;
    }

    public void setPunsionOsea(Boolean punsionOsea) {
        this.punsionOsea = punsionOsea;
    }

    public Boolean getColoides() {
        return coloides;
    }

    public void setColoides(Boolean coloides) {
        this.coloides = coloides;
    }

    public Boolean getHartman() {
        return hartman;
    }

    public void setHartman(Boolean hartman) {
        this.hartman = hartman;
    }

    public Boolean getExposicion() {
        return exposicion;
    }

    public void setExposicion(Boolean exposicion) {
        this.exposicion = exposicion;
    }

    public Boolean getMovimientoBloque() {
        return movimientoBloque;
    }

    public void setMovimientoBloque(Boolean movimientoBloque) {
        this.movimientoBloque = movimientoBloque;
    }

    public Boolean getLavadoCuracion() {
        return lavadoCuracion;
    }

    public void setLavadoCuracion(Boolean lavadoCuracion) {
        this.lavadoCuracion = lavadoCuracion;
    }

    public Boolean getChalecoExtracionVehicular() {
        return chalecoExtracionVehicular;
    }

    public void setChalecoExtracionVehicular(Boolean chalecoExtracionVehicular) {
        this.chalecoExtracionVehicular = chalecoExtracionVehicular;
    }

    public Boolean getMantaTermica() {
        return mantaTermica;
    }

    public void setMantaTermica(Boolean mantaTermica) {
        this.mantaTermica = mantaTermica;
    }

    public Boolean getTablaEspinalLarga() {
        return tablaEspinalLarga;
    }

    public void setTablaEspinalLarga(Boolean tablaEspinalLarga) {
        this.tablaEspinalLarga = tablaEspinalLarga;
    }

    public Boolean getInmovilizacionFerulas() {
        return inmovilizacionFerulas;
    }

    public void setInmovilizacionFerulas(Boolean inmovilizacionFerulas) {
        this.inmovilizacionFerulas = inmovilizacionFerulas;
    }

    public Boolean getTablaEspinalCorta() {
        return tablaEspinalCorta;
    }

    public void setTablaEspinalCorta(Boolean tablaEspinalCorta) {
        this.tablaEspinalCorta = tablaEspinalCorta;
    }

    public String getOtrosProcedimientos() {
        return otrosProcedimientos;
    }

    public void setOtrosProcedimientos(String otrosProcedimientos) {
        this.otrosProcedimientos = otrosProcedimientos;
    }

    public EvaluacionCompletaDto getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(EvaluacionCompletaDto evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Integer getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Integer evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(aspiracionSecreciones);
        dest.writeValue(canulaOrofaringea );
        dest.writeValue(despejeManual );
        dest.writeValue(collarCervical );
        dest.writeValue(canulaNasofaringea );
       dest.writeValue(reanimacionRespiratoria );
        dest.writeValue(canulaNasal );
        dest.writeValue(mascaraNoReinhalacion );
        dest.writeValue(mascaraSimple );
        dest.writeValue(bvm );
       dest.writeValue(rccp );
        dest.writeValue(hemostasia );
        dest.writeValue(dea );
        dest.writeValue(monitoreoSignosVitales );
        dest.writeValue(dispositivoSupragliotico );
        dest.writeValue(cricotiroidotomia );
        dest.writeValue(intubacionOrotraqueal );
        dest.writeValue(descompresionTorax );
        dest.writeValue(ventilacionMecanica );
        dest.writeValue(rehidratacionOral );
        dest.writeValue(ssn09 );
        dest.writeValue(accesoVenosoPeriferico );
        dest.writeValue(accesoVenosoCentral);
        dest.writeValue(destrosa );
        dest.writeValue(punsionOsea );
        dest.writeValue(coloides );
        dest.writeValue(hartman );
        dest.writeValue(exposicion );
        dest.writeValue(movimientoBloque );
        dest.writeValue(lavadoCuracion );
        dest.writeValue(chalecoExtracionVehicular );
        dest.writeValue(mantaTermica );
        dest.writeValue(tablaEspinalLarga );
        dest.writeValue(inmovilizacionFerulas );
        dest.writeValue(tablaEspinalCorta);
        dest.writeValue(otrosProcedimientos);
        dest.writeValue(evaluacion);
        dest.writeValue(evaluacionId);
        dest.writeValue(masajeCardiaco);
        dest.writeValue(puncionOsea);

    }
    public void setupId(ProcedimientosDto procedimientos)
    {
        if(procedimientos != null) {
            id = procedimientos.getId();
            if (evaluacion != null && procedimientos.getEvaluacion() != null) {
                evaluacion.setupId(procedimientos.getEvaluacion());
                evaluacionId = evaluacion.getId();
            }
        }
    }
}
