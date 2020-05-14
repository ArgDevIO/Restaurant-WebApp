import { object, string, ref } from 'yup';

export const registerValidationSchema = object({
    email: string().email('Invalid email').required('Required'),
    password: string().min(5, 'Password is too short - should be 5 chars minimum.').required(),
    confirm_password: string().oneOf([ref('password')], 'Both password need to be the same'),
});
