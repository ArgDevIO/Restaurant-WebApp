import { object, string } from 'yup';

export const logInValidationSchema = object({
    email: string().email('Invalid email').required('Required'),
    password: string()
        .min(8, 'Password must contain at least 8 characters and 1 number')
        .required('Required'),
});
