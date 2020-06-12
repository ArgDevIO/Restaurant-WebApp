import axios from 'axios';
import { notifRequested } from '../../middleware/notifications/actions';
import { emptyCart } from './actions';

const orderEndpoint = 'http://localhost:8080/api/orders/new';

export const handleOrder = (bagItems, userId) => {
    let orderProducts = bagItems.map((bagItem) => {
        return {
            product_id: bagItem.id,
            quantity: bagItem.quantity,
            comment: '',
        };
    });

    let totalPriceAll = bagItems
        .map((bagItem) => {
            return bagItem.totalPrice;
        })
        .reduce((acc, current) => acc + current, 0);

    // console.log(totalPriceAll);

    // console.log(orderProducts);

    const orderData = {
        user_id: userId,
        orderProducts,
        address_id: 1,
        paymentType: 'cash',
        deliveryPrice: 50,
        totalPrice: totalPriceAll,
    };

    console.log('ORDER DATA');
    console.log(orderData);

    return async (dispatch) => {
        try {
            let response = await axios.post(orderEndpoint, orderData, {
                headers: {
                    Authorization: `${localStorage.getItem('token')}`,
                },
            });
            dispatch(
                notifRequested({
                    title: 'SUCCESS',
                    type: 'success',
                    content: {
                        data: {
                            message: 'Order placed sucessfully',
                        },
                    },
                })
            );
            dispatch(emptyCart());
        } catch (e) {
            dispatch(
                notifRequested({
                    title: 'ERROR',
                    type: 'danger',
                    content: {
                        data: {
                            message: 'Order cannot be made',
                        },
                    },
                })
            );
        }
    };
};
