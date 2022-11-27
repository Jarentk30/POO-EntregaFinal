
package gestodatoscontacto;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;



public class FileHandling
{
     private      double Cedula;
     private      String Nombre;
     private      double Salario;
     private      double Telefono;   
     File   Archivo= new File("DatosEmpleados.txt");
     ArrayList<FileHandling> Array =new ArrayList<FileHandling>();
     FileHandling             ObjectTarget = null;
   public FileHandling(double Cedula,String Nombre,double Salario,double Telefono)
    {
         this.Cedula=Cedula;
         this.Nombre=Nombre;
         this.Salario=Salario; 
         this.Telefono=Telefono; 
    }             
   public FileHandling(){} 
   public  double GetCedula()
   {
      return this.Cedula;
    }
   public  String GetNombre()
   {
      return this.Nombre;  
    }
   public  double GetSalario()
   {
      return this.Salario;
    }
   public  double GetCelular()
   {
      return this.Telefono;
    }
  
   public void ComprobarEstadoArchivo(){
        try
      {
        if(!Archivo.exists())
        {
            Archivo.createNewFile();
            System.out.println("El archivo fue creado con exito");
        }else{System.out.println("Ya existe un archivo de datos de contacto");}
       }catch (Exception ex) 
       {    
          System.out.println(ex.getMessage());  
       }
     }
   public void InsertarEmpleados(double CC,String Nombre,double Salario,double Telefono)
      {
         try
      {  
         this.Cedula=CC;
         this.Nombre=Nombre;
         this.Salario=Salario; 
         this.Telefono=Telefono; 
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Archivo,true), "utf-8"));  
          Fescribe.write(GetCedula()+"\t"+GetNombre()+"\t"+GetSalario()+"\t"+GetCelular()+"\r\n");  
          System.out.println("Se ha registrado con exito el nuevo empleado");         
          Fescribe.close();
        }
        catch (Exception ex) 
       {  
             
          System.out.println(ex.getMessage());  
       }
      } 
   private void CasteoObjetoaTxT()
     {
        try
        {
         String linea = null;
         BufferedReader leerFichero = new BufferedReader (new FileReader(Archivo));    
         while( (linea = leerFichero.readLine()) != null)
         {
            StringTokenizer MapeosTokens = new StringTokenizer(linea, "\t");
            String          Cedula =  MapeosTokens.nextToken().trim();
            String          Nombre =  MapeosTokens.nextToken().trim();
            String          Salario =  MapeosTokens.nextToken().trim();
            String          Celular =  MapeosTokens.nextToken().trim();
           
            double    CedulaParseada=Double.parseDouble(Cedula);
            double    SueldoParseado=Double.parseDouble(Salario);
            double    CelularParseado=Double.parseDouble(Celular);
            ObjectTarget= new FileHandling(CedulaParseada,Nombre,SueldoParseado,CelularParseado);
            Array.add(ObjectTarget);
            }
         leerFichero.close();
       
       }catch (Exception ex) 
       {  
          System.out.println(ex.getMessage());  
       }
      }     
   public void LoadArray()
   {
 
      Array.clear();
      CasteoObjetoaTxT(); 
   }
      public void MostrarEmpleados()
      {
     if( Array.size()==0){CasteoObjetoaTxT();}
     System.out.println("=========================== Lista de Empleados=========================================================================================================================================================================================");     
     System.out.println("Cedula" + "             " + "Nombre" + "    " + "Salario ($)" + "  " + "Telefono");
     for(FileHandling n:Array)
     {
      System.out.println(EliminarNotaciónCientífica(n.GetCedula())+"\t"+n.GetNombre()+"\t"+EliminarNotaciónCientífica(n.GetSalario())+"   "+ EliminarNotaciónCientífica(n.GetCelular()));
     }  
     System.out.println("============================Fin del Arrchivo==============================================================================================================================================================================================");
   }
   
   
   public void ModificarRegistrosEmpleado()
  {
    try{
       if( Array.size()==0){CasteoObjetoaTxT();}
        Scanner en =new Scanner(System.in).useDelimiter("\n");
        int Opcion=10;   
        while(Opcion!=7)
             {
               menu();
               Opcion=en.nextInt();
               switch(Opcion)
               {
                    case 1: System.out.println("Introducir la cedula del empleado");
                            double    CCIdentifier=en.nextDouble(); 
                            System.out.println("Introducir la nueva cedula del empleado");
                            double    NumTarget=en.nextDouble();    
                    for(FileHandling n:Array)
                    {
                      if(n.GetCedula()==CCIdentifier){
                        n.Cedula =NumTarget;     
                        System.out.println("=========================== Resumen de la Modificacion=========================================================================================================================================================================================");
                        System.out.println("La nueva cedula del empleado es :"+EliminarNotaciónCientífica(n.GetCedula()));
                        System.out.println("============================FIN==============================================================================================================================================================================================");
                        break;
                      }else{
                          System.out.println("El empleado con cedula: " + CCIdentifier + "no ha sido encontrado" );
                                         }  
                    }
                    break;
                    
                    
                    
                    
                    case 2: System.out.println("Digite el numero de la cedula del empleado al que le quiere modificar el nombre");
                    double  TargetC=en.nextDouble();        
                    String cadenaNueva=en.next();        
                    for(FileHandling n:Array)
                    {
                       
                      if(n.GetCedula() == TargetC){
                          
                        n.Nombre=cadenaNueva;     
                        System.out.println("=========================== Resumen de la modificacion=========================================================================================================================================================================================");
                        System.out.println("El empleado con cedula:"+EliminarNotaciónCientífica(n.GetCedula())+ "ahora su nombre es" + n.GetNombre());
                        System.out.println("============================FIN==============================================================================================================================================================================================");
                        break;
                      }else{
                          System.out.println("el producto no ha sido encontrado");
                       }  
                    }
                    break;
                    case 3: System.out.println("Digite el numero de la cedula del empleado al que le quiere modificar el salario"); 
                    double  TargetD=en.nextDouble();     
                    System.out.println("Digite el salario actualizado");
                    double precioNuevo=en.nextDouble();        
                    for(FileHandling n:Array)
                    {
                       
                      if(n.GetCedula() == TargetD){
                          
                        n.Salario=precioNuevo;     
                        System.out.println("=========================== Resumen de la modificacion=========================================================================================================================================================================================");
                        System.out.println("El empleado con cedula:"+EliminarNotaciónCientífica(n.GetCedula())+ "ahora su salario es" + EliminarNotaciónCientífica(n.GetSalario()));
                        System.out.println("============================FIN==============================================================================================================================================================================================");
                        break;
                      }else{
                          System.out.println("No se ha encontrado el registro");
                      }  
                    }
                    break;
                    
                    case 4: System.out.println("Digite el numero de la cedula del empleado al que le quiere modificar el celular");
                   
                          double TargetE=en.nextDouble(); 
                          System.out.println("Digite el numero nuevo del celular");
                          NumTarget=en.nextDouble();        
                    for(FileHandling n:Array)
                    {
                       
                      if(n.GetCedula() == TargetE){
                          
                        n.Telefono =NumTarget;     
                        System.out.println("=========================== Resumen de la modificacion=========================================================================================================================================================================================");
                        System.out.println("El empleado con cedula:"+EliminarNotaciónCientífica(n.GetCedula())+ "ahora su celular es" + EliminarNotaciónCientífica(n.GetCelular()));
                        System.out.println("============================FIN==============================================================================================================================================================================================");
                        break;
                      }else{
                          System.out.println("El registro no ha sido encontrado");
                      }  
                    }
                    break;
                    case 5: System.out.println("Guardar");
                    try{
                      BufferedWriter bw = new BufferedWriter(new FileWriter(Archivo));
                      for(FileHandling n:Array)
                      {
                          bw.write(n.Cedula+"\t"+n.Nombre+"\t"+ n.Salario+"\t"+ n.Telefono+"\r\n");   
                      }
                      bw.close();
                     }catch (Exception ex) 
                    {  
                      System.out.println(ex.getMessage());  
                    }                  
                    break;  
                    case 6: System.out.println("Digite el numero de la cedula del empleado que desea borrar del archivo");
                            int v=en.nextInt();
                            
                    try{
                      BufferedWriter bw = new BufferedWriter(new FileWriter(Archivo));
                      for(FileHandling n:Array)
                      {  
                          if(n.GetCedula()!=v){
                           bw.write(n.Cedula+ "\t"+n.Nombre+ "\t"+ n.Salario+ "\t"+ n.Telefono+"\r\n");
                        }else{
                          System.out.println("El registro ha sido eliminado");
                                         }
                      }
                      bw.close();
                      Array.clear();
                      CasteoObjetoaTxT();
                      
                     }catch (Exception ex) 
                    {  
  
                      System.out.println(ex.getMessage());  
                    }
                    
                    break;  
                }
            } 
        } catch (Exception ex) 
       {  
          System.out.println(ex.getMessage());  
       }     
     }  
  
  private void menu()
   {
     System.out.println("--------Menu de modificaciones de registros de empleados -------");
     System.out.println("1. Modificar Cedula");
     System.out.println("2. Modificar Nombre ");
     System.out.println("3. Modificar Salario");
     System.out.println("4. Modificar Celular");
     System.out.println("5. Guardar");
     System.out.println("6. Borrar un registro");
     System.out.println("7. Salir");
   }
  public static String EliminarNotaciónCientífica(double número) {
        String d = "####################################";
        return new DecimalFormat("#." + d + d + d).format(número);
    }
}

