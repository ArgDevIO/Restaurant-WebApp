import React from 'react';
import styled from 'styled-components';
import colors from '../../../../theme/colors';
import { FaShoppingBag } from 'react-icons/fa';
import { Link, useHistory } from 'react-router-dom';
import { getBagProducts } from '../../../../redux/bag/reducer';
import { connect } from 'react-redux';
import { removeProductFromBag } from '../../../../redux/bag/actions';
import { getIsLoggedIn } from '../../../../redux/auth/reducer';
import Button from '../../../Button/Button';

const StyledOrder = styled.div`
    background-color: ${(props) => props.theme.palette.main};
    width: 323px;
    height: fit-content;
    border-radius: 10px;
    /* grid-area: orderSection; */
    @media (max-width: 1016px) {
        width: 100%;
    }
`;

const YourOrder = styled.h2`
    color: white;
`;

const OrderSection = ({ bagItems, removeItemFromBag, isLoggedIn }) => {
    const history = useHistory();

    const handleLogIn = () => {
        history.push({
            pathname: '/login',
            state: { from: '/menu' },
        });
    };

    return (
        <StyledOrder>
            <YourOrder>
                <FaShoppingBag /> Your order
            </YourOrder>
            {bagItems.map((bagItem, index) => {
                return (
                    <>
                        <h4>{bagItem}</h4>
                        <button onClick={() => removeItemFromBag(index)}>
                            x
                        </button>
                    </>
                );
            })}
            {/* <Link to="/login"> */}
            {!isLoggedIn ? (
                <Button
                    style={{ width: '90%', margin: `10px auto` }}
                    color={colors.backgroundColor}
                    fullWidth
                    onClick={() => handleLogIn()}
                >
                    Log in
                </Button>
            ) : null}
            {/* </Link> */}
        </StyledOrder>
    );
};

const mapStateToProps = (state) => ({
    bagItems: getBagProducts(state),
    isLoggedIn: getIsLoggedIn(state),
});

const mapDispatchToProps = (dispatch) => ({
    removeItemFromBag: (productIndex) =>
        dispatch(removeProductFromBag(productIndex)),
});

export default connect(mapStateToProps, mapDispatchToProps)(OrderSection);
