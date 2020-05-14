import React from 'react';
import styled from 'styled-components';
import { Formik, Form } from 'formik';
import { registerValidationSchema } from '../../../formik/RegisterValidation';
import axios from 'axios';
import FormikTextInput from '../../../formik/FormikTextInput';
import { GrMail } from 'react-icons/gr';
import colors from '../../../theme/colors';
import { AiOutlineLock } from 'react-icons/ai';
import {
    RegisterOrLoginFormContainer,
    RegisterOrLoginWrapper,
    RegisterOrLoginContainer,
} from '../LogIn/LogIn';
import { LineBreak } from '../../Footer/Footer';
import Button from '../../Button/Button';

const registerInitialValues = {
    email: '',
    password: '',
    confirm_password: '',
};

const RegisterHeader = styled.h2`
    color: ${colors.white};
`;

const handleRegister = (email, password, confirm_password) => {
    // axios.post()
};

const Register = () => (
    <RegisterOrLoginContainer>
        <Formik
            initialValues={registerInitialValues}
            validationSchema={registerValidationSchema}
            onSubmit={({ email, password, confirm_password }) =>
                handleRegister(email, password, confirm_password)
            }
        >
            <RegisterOrLoginFormContainer>
                <Form>
                    <RegisterOrLoginWrapper>
                        <RegisterHeader>Register</RegisterHeader>
                        <LineBreak bottom="20" />
                        <FormikTextInput
                            label="Email"
                            icon={<GrMail color={colors.garden} />}
                            name="email"
                            type="email"
                            id="email"
                        />
                        <FormikTextInput
                            label="Password"
                            icon={<AiOutlineLock color={colors.garden} />}
                            name="password"
                            type="password"
                            id="password"
                        />
                        <FormikTextInput
                            label="Confirm Password"
                            icon={<AiOutlineLock color={colors.garden} />}
                            name="confirm_password"
                            type="password"
                            id="confirm_password"
                        />
                        <br />
                        <Button type="submit" fullWidth>
                            Register
                        </Button>
                    </RegisterOrLoginWrapper>
                </Form>
            </RegisterOrLoginFormContainer>
        </Formik>
    </RegisterOrLoginContainer>
);

export default Register;
