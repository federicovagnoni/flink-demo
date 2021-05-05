package it.coe.model

case class VaccineSomministration(
                                   data_somministrazione: String,
                                   fornitore: String,
                                   area: String,
                                   fascia_anagrafica: String,
                                   sesso_maschile: Int,
                                   sesso_femminile: Int,
                                   categoria_operatori_sanitari_sociosanitari: Int,
                                   categoria_personale_non_sanitario: Int,
                                   categoria_ospiti_rsa: Int,
                                   categoria_60_69: Int,
                                   categoria_70_79: Int,
                                   categoria_over80: Int,
                                   categoria_forze_armate: Int,
                                   categoria_personale_scolastico: Int,
                                   categoria_soggetti_fragili: Int,
                                   categoria_altro: Int,
                                   prima_dose: Int,
                                   seconda_dose: Int,
                                   codice_NUTS1: String,
                                   codice_NUTS2: String,
                                   codice_regione_ISTAT: Int,
                                   nome_area: String
                            )
