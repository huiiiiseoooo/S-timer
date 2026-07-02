import { useState } from 'react';

function App() {
  const [totaltime, setTotaltime] = useState(0);
  const [newIntvervalid, setNewIntervalid] = useState(0);
  
  const handleStart = () => {
    const id = setInterval(() => {
      setTotaltime((prev) => ( prev + 1 ));
      
    }, 1000);
    
    setNewIntervalid(id);
  }

  const handleStop = () => {
    clearInterval(newIntvervalid);
  }

  const seconds = totaltime % 60;
  const minutes = Math.floor((totaltime %3600)/ 60);
  const hours = Math.floor(totaltime / 3600);
  
  return (
    <div className="clock">
      <h1>공부 시간 기록</h1>
      <p><span>{hours}</span> : <span >{minutes}</span> : <span>{seconds}</span></p>
      <button onClick={handleStart}>시작</button>
      <button onClick={handleStop}>정지</button>
    </div>
  );
}

export default App;