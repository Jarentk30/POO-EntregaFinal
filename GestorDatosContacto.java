/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestodatoscontacto;

/**
 *
 * @author liont
 */
public class GestorDatosContacto {
    public static void main (String [ ] args) {
        FileHandling Test = new FileHandling();
        //Comprueba si el archivo ya fue creado
        Test.ComprobarEstadoArchivo();
        //Inserta Nuevos Registros en el archivo
        Test.InsertarEmpleados(1000755279, "Juan Cardenas", 3500000, 5628172);
        Test.InsertarEmpleados(1036953577, "Felipe Garcia", 1500000, 5610676);
        Test.InsertarEmpleados(1036953575, "Ines Jimenez", 4568000, 5982138);
        Test.InsertarEmpleados(1036954579, "Miguel Garcia", 7895213, 6148827);
        //Muesta los registros del archivo
        Test.MostrarEmpleados();
        /*Interactuando con la consola la siguiente instruccion permite modificar el: nombre, apellido, celular, 
        salario de un empleado, asi mismo permite borrar registros del archivo */ 
        Test.ModificarRegistrosEmpleado();
    }
}
