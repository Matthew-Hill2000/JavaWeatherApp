import React, { useState } from 'react';
import './WeatherCompare.css';

const WeatherCompare = () => {
    const [firstCity, setFirstCity] = useState('');
    const [secondCity, setSecondCity] = useState('');
    const [compareType, setCompareType] = useState('rain');
    const [result, setResult] = useState('');
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);

        try {
            const response = await fetch(
                `http://localhost:8080/forecast/compare/${compareType}/${firstCity}/${secondCity}`
            );
            const data = await response.text();
            setResult(data);
        } catch (error) {
            setResult('Error comparing cities. Please try again.');
            console.error('Error:', error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="weather-compare">
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>First City:</label>
                    <input
                        type="text"
                        value={firstCity}
                        onChange={(e) => setFirstCity(e.target.value)}
                        placeholder="Enter first city"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Second City:</label>
                    <input
                        type="text"
                        value={secondCity}
                        onChange={(e) => setSecondCity(e.target.value)}
                        placeholder="Enter second city"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Comparison Type:</label>
                    <select
                        value={compareType}
                        onChange={(e) => setCompareType(e.target.value)}
                    >
                        <option value="rain">Rain</option>
                        <option value="daylight">Daylight Hours</option>
                    </select>
                </div>
                <button
                    type="submit"
                    className="submit-button"
                    disabled={loading}
                >
                    {loading ? 'Comparing...' : 'Compare'}
                </button>
            </form>

            {result && (
                <div className="result">
                    <h3>Result:</h3>
                    <p>{result}</p>
                </div>
            )}
        </div>
    );
};

export default WeatherCompare;