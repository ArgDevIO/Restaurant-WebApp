import React from 'react';
import styled from 'styled-components';
import { FaPhoneAlt } from 'react-icons/fa';
import { FaMapMarkerAlt, FaFacebook, FaInstagram } from 'react-icons/fa';
import { IoMdClock } from 'react-icons/io';
import ManagerLogo from '../../../assets/images/manager.png';
import colors from '../../theme/colors';
import { LineBreak } from '../../components/Footer/Footer';

const ContactContainer = styled.div`
    display: grid;
    grid-auto-flow: column;
    grid-template-columns: 1fr 1fr;
    grid-gap: 20px;
    margin-top: 30px;

    @media (max-width: 1300px) {
        display: grid;
        grid-template-rows: 1fr 1fr;
        grid-template-columns: 1fr;
    }
`;

const ContactWrapper = styled.div`
    /* @media (max-width: 1300px) {
        justify-self: center;
    } */
`;

const ContactMap = styled.div`
    margin-top: 50px;
`;

const ContactItem = styled.div`
    display: flex;
    margin-top: 10px;
    width: 542px;
    height: 70px;
    /* align-items: baseline; */
    /* background-color: red; */
`;

const DoubleTextContactItem = styled.div`
    display: grid;
    place-items: center start;
    font-size: ${(props) => props.theme.sizes.h6};
    color: ${colors.white};
    text-align: start;

    p {
        margin: 0px;
        padding: 0px;
    }

    p:first-child {
        font-size: ${(props) => props.theme.sizes.large};
        color: ${(props) => props.theme.palette.main};
        align-self: end;
    }
`;

const Logo = styled.span`
    color: ${(props) => props.theme.palette.main};
    margin-right: 20px;
    align-self: center;
`;

const Text = styled.p`
    color: ${colors.white};
    font-size: ${(props) => props.theme.sizes.h5};
`;

const ContactUs = styled.div`
    color: ${colors.white};
    font-size: ${(props) => props.theme.sizes.h3};
    text-align: start;
`;

export const GardenColorSpan = styled.span`
    color: ${(props) => props.theme.palette.main};
    font-weight: ${(props) => (props.bold ? `bold` : `normal`)};
`;

const Contact = () => (
    <ContactContainer>
        <ContactWrapper>
            <ContactUs>Contact us</ContactUs>
            <LineBreak top={15} bottom={20} />
            <ContactItem>
                <Logo>
                    <FaPhoneAlt size={40} />
                </Logo>
                <Text>077-700-090</Text>
            </ContactItem>
            <ContactItem>
                <Logo>
                    <img src={ManagerLogo} alt="manager" />
                </Logo>
                <DoubleTextContactItem>
                    <p>Manager</p>
                    <p>Lirim Emini: 077-700-090</p>
                </DoubleTextContactItem>
            </ContactItem>
            <ContactItem>
                <Logo>
                    <FaMapMarkerAlt size={40} />
                </Logo>
                <DoubleTextContactItem>
                    <p>GARDEN - Restaurant</p>
                    <p>Janko Mihajloski br.50 - III/1 MK, Kicevo 6250</p>
                </DoubleTextContactItem>
            </ContactItem>
            <ContactItem>
                <Logo>
                    <IoMdClock size={40} />
                </Logo>
                <DoubleTextContactItem>
                    <p>Hours:</p>
                    <p>
                        <GardenColorSpan>Mon-Sun:</GardenColorSpan> 08:00-23:00
                    </p>
                </DoubleTextContactItem>
            </ContactItem>
            <ContactItem>
                <Logo>
                    <FaMapMarkerAlt size={40} />
                </Logo>
                <DoubleTextContactItem>
                    <p>GARDEN - Restaurant</p>
                    <p>Janko Mihajloski br.50 - III/1 MK, Kicevo 6250</p>
                </DoubleTextContactItem>
            </ContactItem>
            <ContactItem>
                <Logo>
                    <FaFacebook size={40} />
                </Logo>
                <Text>
                    <GardenColorSpan>facebook.com/</GardenColorSpan>
                    gardenrestaurantkercove
                </Text>
            </ContactItem>
            <ContactItem>
                <Logo>
                    <FaInstagram size={40} />
                </Logo>
                <Text>
                    <GardenColorSpan>instagram.com/</GardenColorSpan>
                    gardenrestaurantkercove
                </Text>
            </ContactItem>
        </ContactWrapper>
        <ContactMap>
            <div class="mapouter">
                <div class="gmap_canvas">
                    <iframe
                        title="Garden-Map"
                        width="100%"
                        height="100%"
                        id="gmap_canvas"
                        src="https://maps.google.com/maps?q=Garden%20Restaurant%20Kicevo&t=&z=17&ie=UTF8&iwloc=&output=embed"
                        frameborder="0"
                        scrolling="no"
                        marginheight="0"
                        marginwidth="0"
                    />
                </div>
            </div>
        </ContactMap>
    </ContactContainer>
);
export default Contact;
