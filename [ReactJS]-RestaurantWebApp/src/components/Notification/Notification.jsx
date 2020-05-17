import React, { useEffect } from 'react';
import ReactDOM from 'react-dom';
import NotificationContainer, { CloseIcon } from './NotificationContainer';

const divs = [];

const useTimeout = (callback, duration) => {
    useEffect(() => {
        if (typeof duration === 'number' && duration >= 0) {
            const id = setTimeout(callback, duration);
            return () => clearTimeout(id);
        }
    }, [duration]);
};

const destroy = (index) => {
    if (divs[index]) {
        ReactDOM.unmountComponentAtNode(divs[index]);
        document.body.removeChild(divs[index]);
    }
};

const Notification = ({
    title,
    content,
    variant = '',
    duration = 0,
    close = () => destroy(divs.length),
}) => {
    useTimeout(close, duration * 1000);
    return (
        <NotificationContainer variant={variant} duration={duration}>
            <div>
                <strong>{title}</strong> {content}
            </div>
            <CloseIcon onClick={close} />
        </NotificationContainer>
    );
};

const create = (props, parentDiv) => {
    const divIndex = divs.length;
    const notificationInstance = <Notification {...props} close={() => destroy(divIndex)} />;
    const div = document.createElement('div');
    if (parentDiv) {
        parentDiv.appendChild(div);
    } else if (document.body) {
        document.body.appendChild(div);
    }

    ReactDOM.render(notificationInstance, div);
    divs.push(div);
};

const success = (props, parentDiv) => {
    create({ ...props, variant: 'success' }, parentDiv);
};

const warning = (props, parentDiv) => {
    create({ ...props, variant: 'warning' }, parentDiv);
};

const danger = (props, parentDiv) => {
    create({ ...props, variant: 'danger' }, parentDiv);
};

Notification.create = create;
Notification.success = success;
Notification.warning = warning;
Notification.danger = danger;

export default Notification;
