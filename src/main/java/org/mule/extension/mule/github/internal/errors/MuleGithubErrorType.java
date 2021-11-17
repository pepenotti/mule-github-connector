package org.mule.extension.mule.github.internal.errors;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;
import org.mule.runtime.extension.api.error.MuleErrors;

public enum MuleGithubErrorType implements ErrorTypeDefinition<MuleGithubErrorType> {
    INVALID_CONNECTION(MuleErrors.CONNECTIVITY);

    private ErrorTypeDefinition<? extends Enum<?>> parent;

    MuleGithubErrorType(ErrorTypeDefinition<? extends Enum<?>> parent){
        this.parent = parent;
    }
}
