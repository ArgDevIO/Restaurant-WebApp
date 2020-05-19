import React from 'react';
import styled from 'styled-components';
import { Formik, Form } from 'formik';
import { registerValidationSchema } from '../../formik/RegisterValidation';
import axios from 'axios';
import FormikTextInput from '../../formik/FormikTextInput';
import { GrMail } from 'react-icons/gr';
import colors from '../../theme/colors';
import { AiOutlineLock, AiOutlineNumber } from 'react-icons/ai';
import { MdPhonelinkRing } from 'react-icons/md';
import { GiVillage } from 'react-icons/gi';
import { FiPhoneIncoming, FiUser } from 'react-icons/fi';
import {
    RegisterOrLoginFormContainer,
    RegisterOrLoginWrapper,
    RegisterOrLoginContainer,
} from '../LogIn/LogIn';
import { LineBreak } from '../../components/Footer/Footer';
import Button from '../../components/Button/Button';
import {
    FaAddressBook,
    FaStreetView,
    FaDoorOpen,
    FaBuilding,
    FaMapMarkedAlt,
} from 'react-icons/fa';
import { registerAttempt } from '../../redux/auth/registerUser';
import { connect } from 'react-redux';
import { getDisplayNotification, notificationMessage } from '../../redux/auth/reducer';

const registerInitialValues = {
    fullName: '',
    email: '',
    password: '',
    confirmPassword: '',
    phone: '',
    name: '',
    street: '',
    number: '',
    entry: null,
    appartment: null,
    interphoneCode: null,
    city: 'Kiceco',
    village: '',
    // coordinates: '',
};

const RegisterHeader = styled.h2`
    color: ${colors.white};
`;

const Register = ({ history, registerUser }) => (
    <RegisterOrLoginContainer>
        <Formik
            initialValues={registerInitialValues}
            validationSchema={registerValidationSchema}
            onSubmit={(props) => registerUser(props, history)}
        >
            <RegisterOrLoginFormContainer>
                <Form>
                    <RegisterOrLoginWrapper>
                        <RegisterHeader>Register</RegisterHeader>
                        <LineBreak bottom="20" />
                        <FormikTextInput
                            label="Full name"
                            icon={<FiUser color={colors.garden} />}
                            name="fullName"
                            type="text"
                            id="fullName"
                        />
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
                            name="confirmPassword"
                            type="password"
                            id="confirmPassword"
                        />
                        <FormikTextInput
                            label="Phone"
                            icon={<FiPhoneIncoming color={colors.garden} />}
                            name="phone"
                            type="text"
                            id="phone"
                        />
                        <FormikTextInput
                            label="Address Name"
                            icon={<FaAddressBook color={colors.garden} />}
                            name="name"
                            type="text"
                            id="name"
                        />
                        <FormikTextInput
                            label="Street"
                            icon={<FaStreetView color={colors.garden} />}
                            name="street"
                            type="text"
                            id="street"
                        />
                        <FormikTextInput
                            label="Street number"
                            icon={<AiOutlineNumber color={colors.garden} />}
                            name="number"
                            type="text"
                            id="number"
                        />
                        <FormikTextInput
                            label="Entry"
                            icon={<FaDoorOpen color={colors.garden} />}
                            name="entry"
                            type="text"
                            id="entry"
                        />
                        <FormikTextInput
                            label="Appartment"
                            icon={<FaBuilding color={colors.garden} />}
                            name="appartment"
                            type="text"
                            id="appartment"
                        />
                        <FormikTextInput
                            label="Interphone code"
                            icon={<MdPhonelinkRing color={colors.garden} />}
                            name="interphoneCode"
                            type="text"
                            id="interphoneCode"
                        />
                        <FormikTextInput
                            label="City"
                            icon={<FaMapMarkedAlt color={colors.garden} />}
                            name="city"
                            type="text"
                            id="city"
                            value="Kicevo"
                            disabled
                        />
                        <FormikTextInput
                            label="Village"
                            icon={<GiVillage color={colors.garden} />}
                            name="village"
                            type="text"
                            id="village"
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

const mapDispatchToProps = (dispatch) => ({
    registerUser: (props, history) => dispatch(registerAttempt(props, history)),
});

export default connect(null, mapDispatchToProps)(Register);
