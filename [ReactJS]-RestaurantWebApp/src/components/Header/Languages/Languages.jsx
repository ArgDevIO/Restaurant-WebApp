import React from 'react';
import uuid from 'uuid/dist/v4';
import Language from './Language';

const Languages = ({ languages }) => {
    return languages.map((language) => <Language key={uuid()} language={language.language} />);
};

export default Languages;
