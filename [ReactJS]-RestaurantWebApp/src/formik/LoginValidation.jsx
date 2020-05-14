import { object, string } from 'yup';

export const logInValidationSchema = object({
    email: string()
        .email('Invalid email')
        .required('Required'),
    password: string()
        .min(5, 'Password is too short - should be 5 chars minimum.')
        .required('Required'),
});
