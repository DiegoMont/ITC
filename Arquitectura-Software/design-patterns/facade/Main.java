public class Main {
    public static void main(String[] args) {
        Object form = new Object();
        FormHandlerFacade formHandler = new FormHandlerFacade(form);
        if(formHandler.isFormValid())
            try {
                formHandler.submitForm();    
            } catch (Exception e) {
                //TODO: handle exception
            }  
    }
}
