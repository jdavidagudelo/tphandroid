package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.SignosVitalesDto;

import java.util.Date;

public class SignosVitalesSpringDto {
	private Date fecha;
	private Double respiracion;
	private Double paDiastolica;
	private Double paSistolica;
	private Double glicemia;
	private Double pulso;
	private Double temperatura;
	private Double spo2;
	private EncounterSpringDto encounter;
	public SignosVitalesSpringDto(){
		
	}
    public SignosVitalesSpringDto(SignosVitalesDto signosVitales)
    {
        if(signosVitales != null) {
            fecha = signosVitales.getFecha();
            respiracion = signosVitales.getRespiracion();
            paDiastolica = signosVitales.getPaDiastolica();
            paSistolica = signosVitales.getPaSistolica();
            glicemia = signosVitales.getGlicemia();
            pulso = signosVitales.getPulso();
            temperatura = signosVitales.getTemperatura();
            if (signosVitales.getEncounter() != null) {
                encounter = new EncounterSpringDto(signosVitales.getEncounter());
            }
        }
    }
	/**
	 * @return the encounter
	 */
	public EncounterSpringDto getEncounter() {
		return encounter;
	}
	/**
	 * @param encounter the encounter to set
	 */
	public void setEncounter(EncounterSpringDto encounter) {
		this.encounter = encounter;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the respiracion
	 */
	public Double getRespiracion() {
		return respiracion;
	}
	/**
	 * @param respiracion the respiracion to set
	 */
	public void setRespiracion(Double respiracion) {
		this.respiracion = respiracion;
	}
	/**
	 * @return the paDiastolica
	 */
	public Double getPaDiastolica() {
		return paDiastolica;
	}
	/**
	 * @param paDiastolica the paDiastolica to set
	 */
	public void setPaDiastolica(Double paDiastolica) {
		this.paDiastolica = paDiastolica;
	}
	/**
	 * @return the paSistolica
	 */
	public Double getPaSistolica() {
		return paSistolica;
	}
	/**
	 * @param paSistolica the paSistolica to set
	 */
	public void setPaSistolica(Double paSistolica) {
		this.paSistolica = paSistolica;
	}
	/**
	 * @return the glicemia
	 */
	public Double getGlicemia() {
		return glicemia;
	}
	/**
	 * @param glicemia the glicemia to set
	 */
	public void setGlicemia(Double glicemia) {
		this.glicemia = glicemia;
	}
	/**
	 * @return the pulso
	 */
	public Double getPulso() {
		return pulso;
	}
	/**
	 * @param pulso the pulso to set
	 */
	public void setPulso(Double pulso) {
		this.pulso = pulso;
	}
	/**
	 * @return the temperatura
	 */
	public Double getTemperatura() {
		return temperatura;
	}
	/**
	 * @param temperatura the temperatura to set
	 */
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	/**
	public SignosVitalesSpringDto(){
	 * @return the spo2
	 */
	public Double getSpo2() {
		return spo2;
	}
	/**
	 * @param spo2 the spo2 to set
	 */
	public void setSpo2(Double spo2) {
		this.spo2 = spo2;
	}

}
