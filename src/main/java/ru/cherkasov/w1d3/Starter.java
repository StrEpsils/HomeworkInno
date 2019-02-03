package ru.cherkasov.w1d3;

public class Starter {
    public static void main(String[] args) {
        MathBox<Integer> box1 = new MathBox<>(new Integer[]{1,2,3,4,5});
        MathBox<Integer> box2 = new MathBox<>(new Integer[]{1,2,3,4,5});
        System.out.println(box1.equals(box2));

//        box1.addObject(new ObjectBox());

//        box.summator();
//        box.deleteElement(2);
//        System.out.println(box.toString());
//        Collection xTemp = box.splitter(3);
//        for(Object x: xTemp){
//            System.out.println(x);
//        }

//        ObjectBox objectBox = new ObjectBox(new Integer[]{1,2,3,4,5});
//        objectBox.addObject(25);
//        objectBox.deleteObject(87);
//        System.out.println(objectBox.toString());
//        System.out.println(objectBox.dump());


    }
}
