package br.relesi.rlessa.matchers;

import java.util.Calendar;

public class MachersProprios {

	public static DiaSemanaMatcher cairEm(Integer diaSemana) {
		return new DiaSemanaMatcher(diaSemana);
	}
	
	public static DiaSemanaMatcher cairNumaSegunda() {
		return new DiaSemanaMatcher(Calendar.MONDAY);
	}
}
