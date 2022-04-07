public class FormHandlerFacade implements FormHandler {
    public Object form;

    private FormValidator validator;
    private FormSubmitter submitter;

    public FormHandlerFacade(Object form) {
        validator = new FormValidator();
        submitter = new FormSubmitter();
        this.form = form;
    }

    public boolean isFormValid() {
        checkAllQuestionsAreAnswered(form);
        checkAttachmentsAreValid(form);
        return hasFormPassedAllChecks();
    }
    public void submitForm() throws Exception {
        encodeHtmlSpetialChars(form);
        prepareFileBlobs(form);
        postFormData();
        displaySuccessMsg();
    }
}
