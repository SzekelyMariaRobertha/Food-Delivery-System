package bll;

import dal.Writer;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import presentation.CSearch;
import presentation.EmployeeGUI;
import presentation.MSearch;
import start.Start;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The purpose of this class is to implement methods of an interface in order to do all actions available
 *
 * @author Szekely Maria-Robertha
 * @see bll.IDeliveryServiceProcessing
 */

public class Actions implements IDeliveryServiceProcessing {

    public static List<MenuItem> elements;

    /**
     * The purpose of the method is to import data from a .csv file using lambda expressions
     */

    @Override
    public void importProducts() {

        String fileName = "src\\main\\resources\\products.csv";
        try {
            File inputF = new File(fileName);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            elements = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();

            Start.menuitemslist = (HashMap<String, MenuItem>) elements.stream().collect(Collectors.toMap(MenuItem::getTitle, Function.identity(), (oldValue, newValue) -> oldValue));
            try (FileWriter fileWriter = new FileWriter("src\\main\\resources\\products.txt", true)) {
                for (Map.Entry<String, MenuItem> entry : Start.menuitemslist.entrySet()) {
                    fileWriter.append(entry.getValue().getTitle() + "," + entry.getValue().getRating() + "," + entry.getValue().getCalories() + "," + entry.getValue().getProteins() + "," + entry.getValue().getFats() + "," + entry.getValue().getSodium() + "," + entry.getValue().computePrice() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Function<String, MenuItem> mapToItem = (line) -> {

        String[] content = line.split(","); // a CSV has comma separated lines
        return (MenuItem) new BaseProduct(content[0], Float.parseFloat(content[1]), Float.parseFloat(content[2]), Float.parseFloat(content[3]), Float.parseFloat(content[4]), Float.parseFloat(content[5]), Float.parseFloat(content[6]));
    };


    /**
     * The purpose of the method is to create a new product using datas from certain JTextfields from the interface, add it into a map, and write it into a file
     * @param title new title of the new product
     * @param rating new value for the new product rating
     * @param calories new value for the new product calories
     * @param proteins new value for the new product proteins
     * @param fat new value for the new product fat
     * @param sodium new value for the new product sodium
     * @param price new value for the new product price
     */

    @Override
    public void addProduct(String title, String rating, String calories, String proteins, String fat, String sodium, String price) {

        if (!title.equals("") && !rating.equals("") && !calories.equals("") && !proteins.equals("") && !fat.equals("") && !sodium.equals("") && !price.equals("")) {
            if (!checkParsable(rating) || !checkParsable(calories) || !checkParsable(proteins) || !checkParsable(fat) || !checkParsable(sodium) || !checkParsable(price)) {
                JOptionPane.showMessageDialog(null, "Found a STRING which is not parsable to float");
            } else {
                MenuItem menuItem = new BaseProduct(title + " ", Float.parseFloat(rating), Float.parseFloat(calories), Float.parseFloat(proteins), Float.parseFloat(fat), Float.parseFloat(sodium), Float.parseFloat(price));
                if (!Start.menuitemslist.containsKey(menuItem.getTitle())) {
                    Start.menuitemslist.put(menuItem.getTitle(), menuItem);
                    try (FileWriter fileWriter = new FileWriter("src\\main\\resources\\products.txt", true)) {
                        fileWriter.append(menuItem.getTitle() + "," + menuItem.getRating() + "," + menuItem.getCalories() + "," + menuItem.getProteins() + "," + menuItem.getFats() + "," + menuItem.getSodium() + "," + menuItem.computePrice() + "\n");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete all fields");
        }
    }

    /**
     * The purpose of the method is to delete a product (chosen from the interface through a JComboBox) from a map and a file
     * @param products represents the list of existing products which can be used to choose which product to delete
     */

    @Override
    public void deleteProduct(JComboBox<String> products) {

        if (products.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Nothing to delete");
        } else {
            String s = products.getSelectedItem().toString();
            String[] splt = s.split("\n");
            //System.out.println(splt[0]);

            Start.menuitemslist.remove(splt[0]);

            File inputFile = new File("src\\main\\resources\\products.txt");
            File tempFile = new File("src\\main\\resources\\temp.txt");

            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String lineToRemove = splt[0];
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.contains(lineToRemove)) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

                writer.close();
                reader.close();

                new FileOutputStream(inputFile).close();
                copyData(tempFile, inputFile);

            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    /**
     * The purpose of the method is to modify an existing product (chosen from the interface through a JComboBox) with datas read from certain JTexfields from the intrface
     * @param products represents the list of existing products which can be used to choose which product to modify
     * @param title new title of the new product
     * @param rating new value for the new product rating
     * @param calories new value for the new product calories
     * @param proteins new value for the new product proteins
     * @param fat new value for the new product fat
     * @param sodium new value for the new product sodium
     * @param price new value for the new product price
     */

    @Override
    public void modifyProduct(JComboBox<String> products, String title, String rating, String calories, String proteins, String fat, String sodium, String price) {

        if (products.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nothing to modify");
        } else {
            String[] selectedItem = products.getSelectedItem().toString().split("\n");

            String oldLine = "";

            if (Start.menuitemslist.containsKey(selectedItem[0])) {
                MenuItem menuItem = Start.menuitemslist.get(selectedItem[0]);
                oldLine = menuItem.getTitle() + "," + menuItem.getRating() + "," + menuItem.getCalories() + "," + menuItem.getProteins() + "," + menuItem.getFats() + "," + menuItem.getSodium() + "," + menuItem.computePrice();

                if (!title.equals("")) {
                    menuItem.setTitle(title + " ");
                }
                if (!rating.equals("")) {
                    menuItem.setRating(Float.parseFloat(rating));
                }
                if (!calories.equals("")) {
                    menuItem.setCalories(Float.parseFloat(calories));
                }
                if (!proteins.equals("")) {
                    menuItem.setProteins(Float.parseFloat(proteins));
                }
                if (!fat.equals("")) {
                    menuItem.setFats(Float.parseFloat(fat));
                }
                if (!sodium.equals("")) {
                    menuItem.setSodium(Float.parseFloat(sodium));
                }
                if (!price.equals("")) {
                    menuItem.setPrice(Float.parseFloat(price));
                }

                try {
                    String filePath = "src\\main\\resources\\products.txt";
                    Scanner sc = new Scanner(new File(filePath));
                    StringBuffer buffer = new StringBuffer();
                    while (sc.hasNextLine()) {
                        buffer.append(sc.nextLine() + System.lineSeparator());
                    }
                    String fileContents = buffer.toString();
                    sc.close();
                    String newLine = menuItem.getTitle() + "," + menuItem.getRating() + "," + menuItem.getCalories() + "," + menuItem.getProteins() + "," + menuItem.getFats() + "," + menuItem.getSodium() + "," + menuItem.computePrice();
                    System.out.println("\n" + oldLine);
                    System.out.println(newLine);
                    fileContents = fileContents.replaceAll(oldLine, newLine);
                    FileWriter writer = new FileWriter(filePath);
                    writer.append(fileContents);
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * The purpose of the method is to generate a report which contains filtered orders by a start and an end hour provided from the interface
     * @param start represents the lowerbound of the interval
     * @param end represent the upperbound of the interval
     */

    @Override
    public void btwSEHour(String start, String end) {
        if (EmployeeGUI.map.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nothing to show");
        } else {
            Map<Order, List<MenuItem>> hash = EmployeeGUI.map.entrySet().stream().filter(entry -> (start.compareTo(entry.getKey().getOrderTime()) < 0 && end.compareTo(entry.getKey().getOrderTime()) > 0) || (start.compareTo(entry.getKey().getOrderTime()) == 0) || end.compareTo(entry.getKey().getOrderTime()) == 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            System.out.println("REPORT - Time interval of the orders: " + start + " " + end);
            hash.entrySet().forEach(System.out::println);
            System.out.println();
        }
    }

    /**
     * The purpose of the method is to generate a report which contains the products ordered more than a specified number of times so far
     * @param number represents the minimum number
     */

    @Override
    public void productsOrderedMoreThan(String number) {

        int nr = Integer.parseInt(number);
        HashMap<String, Integer> hash = new HashMap<>();
        for (Map.Entry<Order, List<MenuItem>> entry : EmployeeGUI.map.entrySet()) {
            for (MenuItem menuItem : entry.getValue()) {
                if (!hash.containsKey(menuItem.getTitle())) {
                    hash.put(menuItem.getTitle(), 1);
                } else {
                    hash.replace(menuItem.getTitle(), hash.get(menuItem.getTitle()) + 1);
                }
            }
        }
        hash = (HashMap<String, Integer>) hash.entrySet().stream().filter(x -> x.getValue() > nr).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("REPORT - The products ordered more than a specified number of times so far: " + nr);
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            System.out.println(entry);
        }
        System.out.println();
    }

    /**
     * The purpose of the method is to generate a report which contains the clients that have ordered more than a specified number of times so far and the value of the order was higher than a specified amount
     * @param number represents the minimum number
     * @param value represents the minimum amount
     */

    @Override
    public void clientsOrderedMoreThan(String number, String value) {

        int nr = Integer.parseInt(number);
        int val = Integer.parseInt(value);
        HashMap<String, Integer> hash = new HashMap<>();
        for (Map.Entry<Order, List<MenuItem>> entry : EmployeeGUI.map.entrySet()) {
            if (!hash.containsKey(entry.getKey().getClientUsername())) {
                hash.put(entry.getKey().getClientUsername(), 1);
            } else {
                hash.replace(entry.getKey().getClientUsername(), hash.get(entry.getKey().getClientUsername()) + 1);
            }
        }
        hash = (HashMap<String, Integer>) hash.entrySet().stream().filter(x -> x.getValue() > nr).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        MultiValuedMap<String, Float> finalMap = new ArrayListValuedHashMap<>();

        for(Map.Entry<String, Integer> entry : hash.entrySet()) {
            for(Map.Entry<Order, List<MenuItem>> entryy : EmployeeGUI.map.entrySet()){
                if(entryy.getKey().getClientUsername().equals(entry.getKey())) {
                    finalMap.put(entry.getKey(), entryy.getKey().getTotalPrice());
                }
            }
        }

        //Map<String, Float> finalm = finalMap.entrySet().stream().filter(x -> (Float) x.getValue() > val).collect(Collectors.toMap(Map.Entry::getKey, x -> (Float) x.getValue()));
        Collection<Map.Entry<String, Float>> entries = finalMap.entries();

        System.out.println("REPORT - The clients that have ordered more than a specified number of times so far and the value of the order was higher than a specified amount: " + nr + " " + val);
        for (Map.Entry<String, Float> entry : entries) {
            if(entry.getValue() > val){
                System.out.println(entry);
            }
        }
        System.out.println();
    }

    /**
     * The purpose of the method is to generate a report which contains the products ordered within a specified day with the number of times they have been ordered
     * @param day represents the given day
     */

    @Override
    public void specifiedDay(String day) {

        Map<Order, List<MenuItem>> hashmap = EmployeeGUI.map.entrySet().stream().filter(x -> x.getKey().getOrderData().equals(day)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        HashMap<String, Integer> hash = new HashMap<>();
        for (Map.Entry<Order, List<MenuItem>> entry : hashmap.entrySet()) {
            for (MenuItem menuItem : entry.getValue()) {
                if (!hash.containsKey(menuItem.getTitle())) {
                    hash.put(menuItem.getTitle(), 1);
                } else {
                    hash.replace(menuItem.getTitle(), hash.get(menuItem.getTitle()) + 1);
                }
            }
        }

        System.out.println("REPORT - The products ordered within a specified day with the number of times they have been ordered: " + day);

        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            System.out.println(entry);
        }
        System.out.println();
    }

    /**
     * The purpose of the method is to create a composite product using Composite Design Pattern from a list of base products, add it into a map, and write it into a file
     * @see "https://www.baeldung.com/java-composite-pattern"
     * @param cp represents the composite product
     */

    @Override
    public void createComposite(CompositeProduct cp) {

        List<BaseProduct> composed = new ArrayList<>(cp.getBaseProducts());

        Start.menuitemslist.put(cp.getTitle(), cp);
        try (FileWriter fileWriter = new FileWriter("src\\main\\resources\\products.txt", true)) {
            fileWriter.append(cp.getTitle() + "," + cp.getRating() + "," + cp.getCalories() + "," + cp.getProteins() + "," + cp.getFats() + "," + cp.getSodium() + "," + cp.computePrice() + "COMPOSEDBY");
            for (int i = 0; i < composed.size() - 1; i++) {
                fileWriter.append(composed.get(i).getTitle() + "," + composed.get(i).getRating() + "," + composed.get(i).getCalories() + "," + composed.get(i).getProteins() + "," + composed.get(i).getFats() + "," + composed.get(i).getSodium() + "," + composed.get(i).computePrice() + ";");
            }
            fileWriter.append(composed.get(composed.size() - 1).getTitle() + "," + composed.get(composed.size() - 1).getRating() + "," + composed.get(composed.size() - 1).getCalories() + "," + composed.get(composed.size() - 1).getProteins() + "," + composed.get(composed.size() - 1).getFats() + "," + composed.get(composed.size() - 1).getSodium() + "," + composed.get(composed.size() - 1).computePrice() + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * The purpose of the method is to create an order composed of a name (a hashcode) and a list of items which has been ordered through this order
     * @param name represents the name of the order and it s created using hashcode method
     * @param toOrder the list of items that has been ordered
     * @see Order
     *
     * The method also calls another method that writes into a file the order's details using serialization
     * @see Writer
     * @see Serializable
     */

    @Override
    public void createOrder(String name, List<MenuItem> toOrder) {
        Date date = new Date();
        SimpleDateFormat formatterdate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatterhour = new SimpleDateFormat("HH:mm:ss");
        Order order = new Order(name, formatterdate.format(date), formatterhour.format(date), toOrder);
        Writer writer = new Writer();
        writer.createBill(order);
        Start.observable.addObserver(Start.observer);
        Start.hashMap.put(order, order.getMenuItems());
        Start.observable.setOrder(Start.hashMap);

    }


    /**
     * The purpose of the method is to create a search (filter) system to find products that contain certain words or any char sequence
     * It also sort the product list depending on certain keywords such as "rating, calories, fat, etc."
     * @param filter represents the filter (char sequence) after which the search will be made
     * @param fr can be 1 or 0 depending on where the method is called
     * @see Order
     *
     * The method also calls another method that writes into a file the order's details using serialization
     * @see Writer
     * @see Serializable
     */

    @Override
    public void searchProduct(String filter, int fr) {

        elements = new ArrayList<>(Start.menuitemslist.values());

        if (filter.equals("")) {
            JOptionPane.showMessageDialog(null, "Please insert a filter");
        } else {
            String[] how = filter.toLowerCase().split(" ");
            int i = 0;
            for (String s : how) {
                switch (s) {
                    case "rating":
                        elements.sort(Comparator.comparingDouble(MenuItem::getRating));
                        break;
                    case "calories":
                        elements.sort(Comparator.comparing(MenuItem::getCalories));
                        break;
                    case "proteins":
                        elements.sort(Comparator.comparing(MenuItem::getProteins));
                        break;
                    case "fat":
                        elements.sort(Comparator.comparing(MenuItem::getFats));
                        break;
                    case "sodium":
                        elements.sort(Comparator.comparing(MenuItem::getSodium));
                        break;
                    case "price":
                        elements.sort(Comparator.comparing(MenuItem::computePrice));
                        break;
                    case "title":
                        elements.sort(Comparator.comparing(MenuItem::getTitle));
                        break;
                    default:
                        elements = elements.stream().filter(p -> p.getTitle().toLowerCase().contains(s.toLowerCase())).collect(Collectors.toList());
                }
            }
            if (fr == 0) {
                new CSearch();
            } else {
                new MSearch();
            }
        }
    }

    /**
     * The purpose of the method is to check if the user entered the correct data in the JTextfields on the interface
     * @param s represent which string shoul be checked
     * @return true if the string is parsable to Float, false if the string isn't parsable to Float
     */

    public boolean checkParsable(String s) {
        try {
            Float d = Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method is used when the admin is trying to delete an existing product
     * The old file which contains the old data must be replaced with a new one which contain new data about the deleted product
     * @param file1 represents the new file
     * @param file2 represent the old file
     */

    public void copyData(File file1, File file2) {
        try (FileInputStream inputStream = new FileInputStream(file1); FileOutputStream outputStream = new FileOutputStream(file2)) {
            int i;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}