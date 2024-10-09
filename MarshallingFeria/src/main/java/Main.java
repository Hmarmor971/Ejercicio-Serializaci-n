import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean salir = true;

        do {
            System.out.println(
                    "Elige una opcion:\n1.- Serializar Casetas a XML.\n2.- Desserializar XML.\n3.- Muestrar caseta por ID en XML.\n4.- Serializar a JSON\n5.- Deserializar JSON.\n6.- Mostrar caseta por ID en JSON\n7.- Salir.");

            int x = sc.nextInt();

            switch (x) {
                case 1:
                    Marshalling.marshallingXML();
                    break;
                case 2:
                    Marshalling.unMarshallingXML();
                    break;
                case 3:
                    Marshalling.casetaIdXML();
                    break;
                case 4:
                    Marshalling.MarshalingJSON();
                    break;
                case 5:
                    Marshalling.unMarshallingJSON();
                    break;
                case 6:
                    Marshalling.casetaIdJSON();
                    break;
                case 7:
                    salir = false;
                    break;
            }
        } while (salir);
    }
}
