package br.com.cast.turmaformacao.agenda.util;

import android.widget.EditText;

public final class FormHelper {

    private FormHelper() {
        super();
    }

    public static boolean validateRequired(String requiredMessage, EditText... editTexts) {

        boolean hasRequired = false;

        for (EditText editText : editTexts) {

            String textValue = editText.getText().toString();
            if (textValue.trim().isEmpty()) {
                editText.setError(requiredMessage);
                hasRequired = true;
            }
        }

        return hasRequired;
    }

    public static void clearEditTextFields(EditText... fields){

        for(EditText field : fields){
            field.setText("");
        }

    }
}
