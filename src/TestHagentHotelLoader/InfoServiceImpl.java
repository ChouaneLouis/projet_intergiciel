package TestHagentHotelLoader;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class InfoServiceImpl implements InfoService{

    Map<String, HRecord> hotelMap;

    public InfoServiceImpl(){
        this.hotelMap = initMap();
    }

    @Override
        public HRecord getDataFromName(String name) throws NoSuchElementException {
                if (hotelMap.containsKey(name)) {
                        System.out.println("Sending info...\n");
                        HRecord res = hotelMap.get(name);
                        return res;
                } else {
                        throw new NoSuchElementException();
                }
        }

        private static Map<String, HRecord> initMap() {
                Map<String, HRecord> hotelMap = new HashMap<>();
                try {
                        hotelMap.put("The Golden Horizon Hotel", new HRecordImpl(
                                        "The Golden Horizon Hotel",
                                        "01 45 23 78 90",
                                        "12 Horizon Way, Seaview City"));

                        hotelMap.put("Silverwave Resort & Spa", new HRecordImpl(
                                        "Silverwave Resort & Spa",
                                        "02 58 12 44 87",
                                        "88 Silver Bay Road, Clearwater"));

                        hotelMap.put("Blue Orchid Inn", new HRecordImpl(
                                        "Blue Orchid Inn",
                                        "03 67 89 12 34",
                                        "45 Orchid Street, Bloomtown"));

                        hotelMap.put("Emerald Coast Retreat", new HRecordImpl(
                                        "Emerald Coast Retreat",
                                        "04 79 55 11 90",
                                        "9 Coastline Drive, Emerald Shores"));

                        hotelMap.put("The Grand Maple Lodge", new HRecordImpl(
                                        "The Grand Maple Lodge",
                                        "05 33 47 22 18",
                                        "101 Maple Avenue, Pinebrook"));

                        hotelMap.put("Crimson Peak Hotel", new HRecordImpl(
                                        "Crimson Peak Hotel",
                                        "06 92 55 44 21",
                                        "77 Peak Ridge Road, Redmount"));

                        hotelMap.put("Sunset Crest Suites", new HRecordImpl(
                                        "Sunset Crest Suites",
                                        "07 20 88 63 10",
                                        "34 Sunset Boulevard, Crestfall"));

                        hotelMap.put("The Whispering Pines Resort", new HRecordImpl(
                                        "The Whispering Pines Resort",
                                        "08 41 29 76 54",
                                        "5 Pinewood Lane, Whisper Valley"));

                        hotelMap.put("Oceanview Serenity Hotel", new HRecordImpl(
                                        "Oceanview Serenity Hotel",
                                        "09 84 15 22 77",
                                        "200 Shoreline Road, Oceanglade"));

                        hotelMap.put("Royal Harbor Boutique Hotel", new HRecordImpl(
                                        "Royal Harbor Boutique Hotel",
                                        "01 12 77 98 33",
                                        "3 Harbor Plaza, Kingsport"));

                        hotelMap.put("Mountain Breeze Chalet", new HRecordImpl(
                                        "Mountain Breeze Chalet",
                                        "03 45 67 88 21",
                                        "55 Alpine Trail, Snowcrest"));

                        hotelMap.put("Starlight Haven", new HRecordImpl(
                                        "Starlight Haven",
                                        "04 76 80 11 56",
                                        "8 Starlight Avenue, Nightfall"));

                        hotelMap.put("The Velvet Garden Hotel", new HRecordImpl(
                                        "The Velvet Garden Hotel",
                                        "05 90 12 45 88",
                                        "66 Garden Row, Velvetspring"));

                        hotelMap.put("Crescent Bay Lodge", new HRecordImpl(
                                        "Crescent Bay Lodge",
                                        "06 88 52 77 14",
                                        "19 Moon Crescent, Bayshore"));

                        hotelMap.put("Azure Sky Resort", new HRecordImpl(
                                        "Azure Sky Resort",
                                        "07 15 42 39 71",
                                        "108 Azure Road, Skydale"));

                        hotelMap.put("Amberstone Suites", new HRecordImpl(
                                        "Amberstone Suites",
                                        "08 99 60 77 23",
                                        "11 Amberstone Way, Goldfield"));

                        hotelMap.put("Riverside Lantern Inn", new HRecordImpl(
                                        "Riverside Lantern Inn",
                                        "09 28 35 40 92",
                                        "2 Riverside Lane, Lanternford"));

                        hotelMap.put("Moonridge Palace", new HRecordImpl(
                                        "Moonridge Palace",
                                        "02 77 91 55 80",
                                        "70 Palace Road, Moonridge"));

                        hotelMap.put("Harborlight Grand Hotel", new HRecordImpl(
                                        "Harborlight Grand Hotel",
                                        "03 55 90 33 21",
                                        "44 Grand Harbor Street, Lightbay"));

                        hotelMap.put("Sapphire Spring Retreat", new HRecordImpl(
                                        "Sapphire Spring Retreat",
                                        "04 10 63 77 91",
                                        "6 Sapphire Spring Road, Clearfalls"));
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return hotelMap;
        }
}