package com.example.pmdm_ut05_tarea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hero implements Serializable {
    private int id;
    private String realName;
    private String heroName;
    private String description;

    public Hero(int id, String realName, String heroName, String description) {
        this.id = id;
        this.realName = realName;
        this.heroName = heroName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + ". " + realName + " - " + heroName;
    }

    public static List<Hero> generateHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Hero(1, "Steve Rogers", "Captain America", "Super soldado con escudo de vibranium"));
        heroes.add(new Hero(2, "Thor Odinson", "Thor", "Dios del trueno con el martillo Mjolnir"));
        heroes.add(new Hero(3, "Bruce Banner", "Hulk", "Científico que se convierte en gigante verde"));
        heroes.add(new Hero(4, "Tony Stark", "Iron Man", "Genio multimillonario con armadura tecnológica"));
        heroes.add(new Hero(5, "Natasha Romanoff", "Black Widow", "Espía experta en combate cuerpo a cuerpo"));
        heroes.add(new Hero(6, "Clint Barton", "Hawkeye", "Maestro arquero y agente de SHIELD"));
        heroes.add(new Hero(7, "Peter Parker", "Spider-Man", "Héroe con habilidades arácnidas"));
        heroes.add(new Hero(8, "T'Challa", "Black Panther", "Rey de Wakanda con traje de vibranium"));
        heroes.add(new Hero(9, "Stephen Strange", "Doctor Strange", "Hechicero supremo y maestro de las artes místicas"));
        heroes.add(new Hero(10, "Carol Danvers", "Captain Marvel", "Heroína con habilidades cósmicas"));
        heroes.add(new Hero(11, "Wanda Maximoff", "Scarlet Witch", "Bruja con poderes de alteración de la realidad"));
        heroes.add(new Hero(12, "Vision", "Vision", "Androide sintético con gema mental"));
        heroes.add(new Hero(13, "Scott Lang", "Ant-Man", "Ladrón convertido en héroe con traje que altera el tamaño"));
        heroes.add(new Hero(14, "Hope van Dyne", "Wasp", "Héroe con alas y traje tecnológico"));
        heroes.add(new Hero(15, "Sam Wilson", "Falcon", "Ex-soldado con traje de vuelo avanzado"));
        heroes.add(new Hero(16, "James Rhodes", "War Machine", "Soldado con armadura militarizada de Iron Man"));
        heroes.add(new Hero(17, "Bucky Barnes", "Winter Soldier", "Asesino convertido en héroe con brazo biónico"));
        heroes.add(new Hero(18, "Loki Laufeyson", "Loki", "Dios del engaño y hermano de Thor"));
        heroes.add(new Hero(19, "Gamora", "Gamora", "Guerrera letal e hija adoptiva de Thanos"));
        heroes.add(new Hero(20, "Drax el Destructor", "Drax", "Guerrero en busca de venganza contra Thanos"));
        return heroes;
    }
}
