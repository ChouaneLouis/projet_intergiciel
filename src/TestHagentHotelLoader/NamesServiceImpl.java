package TestHagentHotelLoader;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

public class NamesServiceImpl implements NamesService{

    private List<String> names;

    public NamesServiceImpl() {
        this.names = new LinkedList<>(Arrays.asList(
                "The Golden Horizon Hotel",
                "Silverwave Resort & Spa",
                "Blue Orchid Inn",
                "Emerald Coast Retreat",
                "The Grand Maple Lodge",
                "Crimson Peak Hotel",
                "Sunset Crest Suites",
                "The Whispering Pines Resort",
                "Oceanview Serenity Hotel",
                "Royal Harbor Boutique Hotel",
                "Mountain Breeze Chalet",
                "Starlight Haven",
                "The Velvet Garden Hotel",
                "Crescent Bay Lodge",
                "Azure Sky Resort",
                "Amberstone Suites",
                "Riverside Lantern Inn",
                "Moonridge Palace",
                "Harborlight Grand Hotel",
                "Sapphire Spring Retreat"));
    }

    @Override
    public List<String> getNames(){
        System.out.println("Sending names...\n");
        return this.names;
    }
}