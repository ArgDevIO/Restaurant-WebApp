export const NOTIFICATION_DISPLAY_REQUEST = 'NOTIFICAION_DISPLAY_REQUEST';

export const notifRequested = (notification) => ({
    type: NOTIFICATION_DISPLAY_REQUEST,
    payload: {
        ...notification,
    },
});
