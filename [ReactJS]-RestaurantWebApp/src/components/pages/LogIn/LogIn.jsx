import React from 'react';
import { connect } from 'react-redux';
import { getIsLoggedIn, getPreload } from '../../../redux/auth/reducer';
import { loginToggler } from '../../../redux/auth/actions';
import styled from 'styled-components';
import { useHistory } from 'react-router';
import colors from '../../../theme/colors';
import { FaFacebookF } from 'react-icons/fa';
import { GrMail } from 'react-icons/gr';
import { LineBreak } from '../../Footer/Footer';
import { AiOutlineLock } from 'react-icons/ai';
import { fetchAuthUserData } from '../../../redux/auth/fetchAuthUserData';
import { useState } from 'react';
import Button from '../../Button/Button';
import Input from '../../Input/Input';
import { logInValidationSchema } from '../../../formik/LoginValidation';
import { Formik, Form } from 'formik';
import FormikTextInput from '../../../formik/FormikTextInput';
import { Link } from 'react-router-dom';

export const RegisterOrLoginContainer = styled.div`
    display: grid;
    place-items: center;
    /* grid-template-columns: minmax(441px, 1fr); */
    height: 80%;
`;

export const RegisterOrLoginFormContainer = styled.div`
    height: fit-content;
    width: 441px;
    background: ${colors.backgroundColor};
    border-radius: 10px;
    box-shadow: 0px 3px 6px #00000029;
`;

export const RegisterOrLoginWrapper = styled.div`
    width: 80%;
    margin: 10% auto 10% auto;
`;

const LoginHeader = styled.h2`
    color: ${colors.white};
`;

const LineStyles = styled.div`
    display: flex;
    justify-content: baseline;
    color: white;

    div {
        align-self: center;
        margin: 7px;
    }
`;

const ForgotPassword = styled(Link)`
    color: white;
`;
const CustomLink = styled(Link)`
    color: ${({ color, theme }) => color || theme.palette.main};
`;

const logInInitialValues = {
    email: '',
    password: '',
};

const LogIn = (props) => {
    const history = useHistory();
    // const [username, setUsername] = useState('');
    // const [password, setPassword] = useState('');

    const handleLogin = (e) => {
        // e.preventDefault();
        // let response = props.loginUser(username, password);
        // response.then((data) => {
        //     console.log(data);
        //     if (data) history.push('/menu');
        // });
        // for protected routes
        // let pathToGo = props.history.location.state.from.pathname;
        // let pathToGo = props.location.state;
        // if (!pathToGo) {
        //     console.log('PATH TO GO NOT NULL');
        //     console.log(pathToGo);
        //     history.push(pathToGo);
        // } else {
        //     history.push('/menu');
        // }
        // history.push('/menu');
    };

    if (props.loading) return <p>Loading</p>;

    const handleSubmit = (email, password) => {
        props.loginUser(email, password).then((response) => {
            if (response) {
                // const pathToGo = props.location.state;
                // console.log("PATH TO GO: ", pathToGo);
                // if (pathToGo === null) history.push('/menu');
                // history.push(pathToGo.from);
                history.push('/menu');
            }
        });
    };

    return (
        <RegisterOrLoginContainer>
            <Formik
                initialValues={logInInitialValues}
                validationSchema={logInValidationSchema}
                onSubmit={({ email, password }) => handleSubmit(email, password)}
            >
                <RegisterOrLoginFormContainer>
                    <Form>
                        <RegisterOrLoginWrapper>
                            <LoginHeader>Login to place orders</LoginHeader>
                            <Button type="button" color={colors.navy} fullWidth>
                                <FaFacebookF
                                    size={22}
                                    color={colors.white}
                                    href="www.facebook.com"
                                />
                            </Button>
                            <LineStyles>
                                <LineBreak />
                                <p>or</p>
                                <LineBreak />
                            </LineStyles>
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
                            <br />
                            <Button type="submit" fullWidth>
                                Login
                            </Button>
                            <br />
                            <CustomLink color="white" to="/forgot-password">
                                Forgot password?
                            </CustomLink>
                            <br />
                            <br />
                            <CustomLink color="white" to="/register">
                                Do not have an account? Register here
                            </CustomLink>
                        </RegisterOrLoginWrapper>
                    </Form>
                </RegisterOrLoginFormContainer>
            </Formik>
        </RegisterOrLoginContainer>
    );
};

const mapStateToProps = (state) => ({
    isLoggedIn: getIsLoggedIn(state),
    loading: getPreload(state),
});

const mapDispatchToProps = (dispatch) => ({
    loginUser: (email, password) => dispatch(fetchAuthUserData(email, password)),
});

export default connect(mapStateToProps, mapDispatchToProps)(LogIn);
