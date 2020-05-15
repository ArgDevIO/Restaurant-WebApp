import { object, string, ref, number } from 'yup';

const phoneNumberPattern = /^(07[012578]\d{6}$)/;
const passwordPattern = /(?=\D*\d)\S{8,}$/;

export const registerValidationSchema = object({
    fullName: string().min(5, 'Too short - should be 5 chars minimum').required('Required'),
    email: string().email('Invalid email').required('Required'),
    password: string()
        .min(5, 'Password is too short - should be 5 chars minimum.')
        .required('Required'),
    confirmPassword: string()
        .oneOf([ref('password')], 'Both password need to be the same')
        .required('Required'),
    phone: string().matches(phoneNumberPattern, 'Invalid phone number').required('Required'),
    name: string().required('Required'),
    street: string().required('Required'),
    number: number(),
    entry: string().nullable(),
    appartment: string().nullable(),
    interphoneCode: string().nullable(),
    city: string(),
    village: string(),
});
