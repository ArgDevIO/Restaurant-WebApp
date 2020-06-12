import Notification from '../../components/Notification/Notification';
import { NOTIFICATION_DISPLAY_REQUEST } from './actions';

export default (store) => (next) => (action) => {
    if (action.type !== NOTIFICATION_DISPLAY_REQUEST) {
        return next(action);
    }

    const { type, content, title } = action.payload;

    Notification.create({
        variant: type,
        title,
        content: content.data.message,
        duration: 3,
    });
};
