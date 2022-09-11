import icon from '../../assets/img/notification-icon.svg'

import './styles.css'

function NotificationButton() {
  return (
    <div className="dsmeta-red-btn"
      // onClick={()=> {handleClick(saleId)}}
    >
      <img src={icon} alt="Notificar" />
    </div>
  )
}

export default NotificationButton;
